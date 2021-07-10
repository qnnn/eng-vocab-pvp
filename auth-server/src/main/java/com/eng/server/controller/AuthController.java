package com.eng.server.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    // 授权码模式
    @RequestMapping(value = "/authorize",params = "grant_type=authorization_code")
    public String authorizationCode(Model model){

        return "xx";
    }




}
