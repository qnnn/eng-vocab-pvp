package com.eng.server.controller;


import cn.hutool.core.util.ObjectUtil;
import com.eng.server.domian.EasyWord;
import com.eng.server.service.PickWordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    private PickWordService pickWordService;


    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/getCurrentUser")
    public String getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);
        return authentication.toString();
    }


    /**
     * 获取对战单词，从每个难度选2个
     * @return List<EasyWord>
     */
    @GetMapping("getCompetitor")
    public List<EasyWord> getCompetitorWord(){
        List<EasyWord> easyWords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            easyWords.addAll(pickWordService.getcet4Wordlevel(2, i));
        }
        return easyWords;
    }

    @GetMapping("getCompetitorChinese")
    public List<String> getCompetitorChinese(){
        return pickWordService.getcet4Chinese(30);
    }




}
