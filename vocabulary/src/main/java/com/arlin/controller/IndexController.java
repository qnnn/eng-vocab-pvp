package com.arlin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return "index";
    }
}
