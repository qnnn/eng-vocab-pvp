package com.arlin.controller;

import com.arlin.constants.StatusConst;
import com.arlin.dto.Result;
import com.arlin.entity.Student;
import com.arlin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/25
 */
@Controller
public class LoginController {

    @Autowired
    StudentService studentService;

    @RequestMapping("login")
    public String loginPage() {
        return "index";
    }

    @RequestMapping("")
    public String indexPage(){
        return "index";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String sno,
//                        @RequestParam String password,
//                        HttpSession session,
//                        RedirectAttributes attributes) {
//        Student student = studentService.login(Student.builder().sno(Long.parseLong(sno)).password(password).build());
//        if(student == null) {
//            attributes.addFlashAttribute("message", "学号或密码错误");
//            return "redirect:/login";
//        }
//        session.setAttribute("Student", student);
//        return "redirect:/";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("Student");
        return "redirect:/login";
    }
}
