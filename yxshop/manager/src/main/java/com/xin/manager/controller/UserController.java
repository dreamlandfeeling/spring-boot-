package com.xin.manager.controller;

import com.alibaba.fastjson.JSON;
import com.xin.manager.model.TbUser;
import com.xin.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password){
        TbUser tbUser = userService.selectByUsernameAndPassword(username, password);
        return JSON.toJSONString(tbUser);
    }
}
