package com.eng.client.controller;


import com.eng.client.feign.ClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BusinessController {

    @Resource
    ClientFeign clientFeign;


    @RequestMapping({"","index"})
    public String index(){

        return "index";

    }

    @ResponseBody
    @RequestMapping("xx")
    public String ces(){

        String currentUser = clientFeign.getCurrentUser();
        System.out.println(currentUser);
        return "poiyui";
    }




}
