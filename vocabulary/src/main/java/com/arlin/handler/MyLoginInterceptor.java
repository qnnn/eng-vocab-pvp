package com.arlin.handler;

import com.arlin.entity.Student;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyLoginInterceptor
 * @Description: TODO
 * @Author: arlin
 * @Date: 2021/6/26
 */
public class MyLoginInterceptor  {
//    implements HandlerInterceptor
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Student user = (Student) request.getSession().getAttribute("Student");
//        if (user == null) {
//            System.out.println("该用户没有登录");
//            response.sendRedirect("/login");
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
