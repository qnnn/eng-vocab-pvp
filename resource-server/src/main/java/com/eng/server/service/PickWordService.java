package com.eng.server.service;

import com.eng.server.domian.EasyWord;
import com.eng.server.utils.MapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PickWordService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public List<EasyWord> getcet4Wordlevel(Integer num, Integer level)  {

        List<Object> objects = redisTemplate.opsForSet().randomMembers(String.format("word_cet4: level-%d", level), num);
        assert objects!=null;
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

    public List<String> getcet4Chinese(Integer num){
        List<Object> objects = redisTemplate.opsForSet().randomMembers("word_cet4: chinese", num);
        assert objects!=null;
        List<String> chinese = new ArrayList<>();
        for (Object object : objects) {
            chinese.add(object.toString());
        }
        return chinese;
    }


    public List<EasyWord> getcet6Wordlevel(Integer num,Integer level) {
        List<Object> objects = redisTemplate.opsForSet().randomMembers(String.format("word_cet6: level-%d", level), num);
        assert objects!=null;
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

    public List<EasyWord> getkyWordlevel(Integer num,Integer level) {
        List<Object> objects = redisTemplate.opsForSet().randomMembers(String.format("word_cet6: level-%d", level), num);
        assert objects!=null;
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


}
