package com.eng.client.service;

import com.eng.client.dto.CompetitorWord;
import com.eng.client.dto.EasyWord;
import com.eng.client.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Order(1)
public class WordService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 出题目
     * @param word 单词
     * @param subjectIndex 得到的题目
     * @return 拼接的题目
     */
    public String doConfuse(CompetitorWord word,Integer subjectIndex){
        int index = (int) (Math.random() * 4+0);
        List<String> list = new ArrayList<>(4);
        list.add(word.getWrongDefinition1());
        list.add(word.getWrongDefinition2());
        list.add(word.getWrongDefinition3());
        list.add(index,word.getDefinition());
        StringBuilder sb = new StringBuilder();
        sb.append("subject:").append(subjectIndex);
        sb.append(word.getWord()).append("AA");
        for (String s : list) {
            sb.append(s).append("AA");
        }

        return sb.toString();
    }

    public List<CompetitorWord> getSubject() {
        List<EasyWord> easyWords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 每个等级抽两个
            easyWords.addAll(getcet4Wordlevel(2, i));
        }
        List<String> wrongDefinitions = getcet4Chinese(30);
        List<CompetitorWord> competitorWords = new ArrayList<>();
        for (EasyWord easyWord : easyWords) {
            CompetitorWord competitorWord = new CompetitorWord(easyWord.getWord(), easyWord.getDefinition(),
                    wrongDefinitions.remove(0), wrongDefinitions.remove(0), wrongDefinitions.remove(0));
            competitorWords.add(competitorWord);
        }
        return competitorWords;
    }


    private List<EasyWord> getcet4Wordlevel(Integer num, Integer level) {

        List<Object> objects = redisTemplate.opsForSet().randomMembers(String.format("word_cet4: level-%d", level), num);
        assert objects != null;
        List<EasyWord> easyWords = new ArrayList<>();
        for (Object object : objects) {
            EasyWord easyWord = null;
            try {
                easyWord = MapperUtils.json2pojo(object.toString(), EasyWord.class);
                easyWords.add(easyWord);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return easyWords;
    }

    private List<String> getcet4Chinese(Integer num) {
        List<Object> objects = redisTemplate.opsForSet().randomMembers("word_cet4: chinese", num);
        assert objects != null;
        List<String> chinese = new ArrayList<>();
        for (Object object : objects) {
            chinese.add(object.toString());
        }
        return chinese;
    }
}
