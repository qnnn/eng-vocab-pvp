package com.arlin.controller;

import com.arlin.constants.StatusConst;
import com.arlin.dto.CommitDTO;
import com.arlin.dto.ResultWord;
import com.arlin.dto.WordDTO;
import com.arlin.entity.Student;
import com.arlin.service.StudentService;
import com.arlin.service.WordService;
import com.arlin.dto.Result;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: WordController
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/24
 */
@RestController
public class WordController {

    private final WordService wordService;

    @Autowired
    StudentService studentService;


    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @RequestMapping("/token")
    public Result<String> getToken(Authentication authentication){
        OAuth2AuthenticationDetails details =  (OAuth2AuthenticationDetails) authentication.getDetails();
        System.out.println(details.getTokenValue());
        return new Result<>(true,StatusConst.OK,"",details.getTokenValue());
    }

    @GetMapping("/words")
    public Result<ResultWord> GetWordsList(@Param("type") Integer type,HttpSession session,Authentication authentication) {
        String student = authentication.getName();
        session.setAttribute("Student", student);
        List<WordDTO> wordDTOS = wordService.listWordByGrade(type, 3);
        List<String> words = new ArrayList<>();
        for(int i=0; i<wordDTOS.size(); i++) {
            words.add(wordDTOS.get(i).getWord());
        }
        ResultWord resultWord = new ResultWord(words, 3);
        return new Result<>(true, StatusConst.OK, "操作成功", resultWord);
    }

    @PostMapping("/words")
    public Result<ResultWord> WordsList(HttpSession session,CommitDTO commitDTO) {
        System.out.println(commitDTO);
        String sno =  session.getAttribute("Student").toString();
        Student student = studentService.getById(Long.valueOf(sno));
        System.out.println(sno);
        ImmutablePair<List<WordDTO>, Integer> pair = wordService.listWordByGrade(student.getSno(), commitDTO);
        List<String> words = new ArrayList<>();
        for(int i=0; i<pair.getLeft().size(); i++) {
            words.add(pair.getLeft().get(i).getWord());
        }
        ResultWord resultWord = new ResultWord(words, pair.getRight());
        return new Result<>(true, StatusConst.OK, "操作成功", resultWord);
    }

    @PostMapping("/score")
    public Result<Integer> score(HttpSession session, CommitDTO commitDTO) {
        String sno =  session.getAttribute("Student").toString();
        Student student = studentService.getById(Long.valueOf(sno));
        return new Result<>(true, StatusConst.OK, "计算结果成功", wordService.getScore(student.getSno(), commitDTO));
    }
}
