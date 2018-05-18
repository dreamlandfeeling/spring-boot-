package com.xin.manager.controller;

import com.xin.manager.model.User;
import com.xin.manager.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String loginUser(String username,String password,HttpSession session) {
        password = MD5Utils.md5(password);
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            User user=(User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch(Exception e) {
            session.setAttribute("error", "用户名或者密码错误");
            return "login";//返回登录页面
        }

    }
    @GetMapping("/quit")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
//        session.removeAttribute("user");
        return "login";
    }
}
