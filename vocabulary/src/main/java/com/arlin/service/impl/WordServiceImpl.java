package com.arlin.service.impl;

import com.arlin.dao.HistoryDao;
import com.arlin.dao.StudentDao;
import com.arlin.dao.WordDao;
import com.arlin.dto.CommitDTO;
import com.arlin.dto.WordDTO;
import com.arlin.entity.CommitResult;
import com.arlin.entity.History;
import com.arlin.entity.Student;
import com.arlin.entity.Word;
import com.arlin.enums.GradeEnum;
import com.arlin.enums.WordEnum;
import com.arlin.service.WordService;
import com.arlin.utils.CommitUtil;
import com.arlin.utils.WordUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import cn.hutool.core.util.ArrayUtil;

import java.util.*;

/**
 * @ClassName: WordServiceImpl
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/23
 */
@Service
public class WordServiceImpl extends ServiceImpl<WordDao, Word> implements WordService {

    private final WordDao wordDao;
    private final StudentDao studentDao;
    private final HistoryDao historyDao;
    private final RedisTemplate redisTemplate;

    public WordServiceImpl(WordDao wordDao, StudentDao studentDao, HistoryDao historyDao, RedisTemplate redisTemplate) {
        this.wordDao = wordDao;
        this.studentDao = studentDao;
        this.historyDao = historyDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<WordDTO> listWordByGrade(Integer type, Integer grade) {
        Integer[] nums = ArrayUtil.clone(GradeEnum.values()[grade - 1].getNums());
        List<Integer> idList = WordUtil.createWordIdList(type, nums);
        List<Word> wordList = wordDao.selectList(new LambdaQueryWrapper<Word>()
                .select(Word::getId, Word::getWord, Word::getSort, Word::getCollins, Word::getGrade)
                .eq(Word::getType, type)
                .in(Word::getId, idList));
        while(wordList.size() != 50) {
            idList = WordUtil.createWordIdList(type, nums);
            wordList = wordDao.selectList(new LambdaQueryWrapper<Word>()
                    .select(Word::getId, Word::getWord, Word::getSort, Word::getCollins, Word::getGrade)
                    .eq(Word::getType, type)
                    .in(Word::getId, idList));
        }
        List<WordDTO> wordDTOS = new ArrayList<>();
        for(Word word : wordList) {
            wordDTOS.add(new WordDTO(word.getWord(), type, word.getGrade()));
        }
        return wordDTOS;
    }

    @Override
    public ImmutablePair<List<WordDTO>, Integer> listWordByGrade(Long sno, CommitDTO commitDTO) {
        for(int i = 0; i < commitDTO.getWords().size(); i++) {
            String wordValue = (String) redisTemplate.opsForHash().get(WordEnum.values()[commitDTO.getType() - 1].getName(), commitDTO.getWords().get(i));
            Integer grade = Integer.parseInt(wordValue.split("-")[0]);
            WordDTO wordDTO = new WordDTO(commitDTO.getWords().get(i), commitDTO.getType(), grade);
            commitDTO.add(wordDTO);
        }
        //计算各个等级的数量
        CommitResult commitResult = CommitUtil.calculateResult(commitDTO);
        //计算得分
        Integer score = WordUtil.calculateScore(commitResult, commitDTO.getType());
        System.out.println("第"+ commitDTO.getPage() + "次，结果score：" + score);
        //计算下一个等级
        Integer nextGrade = WordUtil.calculateNextGrade(commitDTO.getType(), commitDTO.getGrade(), score);
        System.out.println("下一个等级：" + nextGrade);
        //将结果存入redis，第2、3
        if(commitDTO.getPage() == 1) {
            redisTemplate.opsForZSet().removeRange(sno.toString(), 0, -1);
        }
        redisTemplate.opsForZSet().add(sno.toString(), score, score);

        //包装临时对象
        ImmutablePair<List<WordDTO>, Integer> pair = ImmutablePair.of(listWordByGrade(commitDTO.getType(), nextGrade), nextGrade);

        return pair;
    }

    @Override
    public Integer getScore(Long sno, CommitDTO commitDTO) {
        for(int i = 0; i < commitDTO.getWords().size(); i++) {
            String wordValue = (String) redisTemplate.opsForHash().get(WordEnum.values()[commitDTO.getType() - 1].getName(), commitDTO.getWords().get(i));
            Integer grade = Integer.parseInt(wordValue.split("-")[0]);
            WordDTO wordDTO = new WordDTO(commitDTO.getWords().get(i), commitDTO.getType(), grade);
            commitDTO.add(wordDTO);
        }
        //计算各个等级的数量
        CommitResult commitResult = CommitUtil.calculateResult(commitDTO);
        //计算得分
        Integer score = WordUtil.calculateScore(commitResult, commitDTO.getType());
        System.out.println("第"+ commitDTO.getPage() + "次，结果score：" + score);
        redisTemplate.opsForZSet().add(sno.toString(), score, score);
        Integer scoreTotal = 0;
        Integer size = Math.toIntExact(redisTemplate.opsForZSet().zCard(sno.toString()));
        if(commitDTO.getPage() == 3) {
            Object[] range = redisTemplate.boundZSetOps(sno.toString()).range(0, -1).toArray();
            //计算总分数
            for(int i = 0; i < range.length; i++) {
                System.out.println(range[i]);
                scoreTotal += (Integer) range[i];
            }
            //计算平均分
            scoreTotal /= size;
            //删除redis中的key
            redisTemplate.delete(sno.toString());
            Student student = studentDao.selectById(sno);
            //存入数据库
            History history = History.builder()
                    .sno(sno)
                    .sName(student.getSName())
                    .type(commitDTO.getType())
                    .score(scoreTotal)
                    .createTime(new Date())
                    .build();
            historyDao.insert(history);
        }
        return scoreTotal;
    }

    @Override
    public void calculateGradeToRedis() {
//        int[] num = new int[]{1034, 1034, 1034, 1034, 1038};
        int[] num = new int[]{938, 938, 938, 938, 938};
        int flag = 8915;
        for(int i = 0; i < num.length; i++) {
            List<Word> words = wordDao.selectList(new LambdaQueryWrapper<Word>()
                    .select(Word::getWord, Word::getSort, Word::getCollins)
                    .eq(Word::getType, 3)
                    .gt(Word::getId, flag)
                    .last("limit " + num[i]));
            flag += num[i];
            for(Word word : words) {
                redisTemplate.opsForHash().put("word_ky_grade" + (i+1), word.getWord(), word.getSort() + "-" + word.getCollins());
            }
        }
    }

}
