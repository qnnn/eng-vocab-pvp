package com.eng.client.controller;

import com.eng.client.dto.CompetitorWord;
import com.eng.client.entity.EasyWord;
import com.eng.client.feign.ClientFeign;
//import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CompetitorController {

    @Resource
    ClientFeign clientFeign;

    @RequestMapping("/getCompetitor")
    public List<CompetitorWord> getCompetitor(){
        List<EasyWord> easyWords = clientFeign.getCompetitorWord();
        List<String> competitorChinese = clientFeign.getCompetitorChinese();
        List<CompetitorWord> competitorWords = new ArrayList<>();
        for (EasyWord easyWord : easyWords) {
            CompetitorWord competitorWord = new CompetitorWord(easyWord.getWord(),easyWord.getDefinition(),competitorChinese.remove(0),competitorChinese.remove(0),
                    competitorChinese.remove(0));
            competitorWords.add(competitorWord);
        }

        return competitorWords;
    }

    @GetMapping("/getCurrentSno")
    public String cse(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }



}
