package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.model.TbUser;
import com.xin.top.service.UserService;
import com.xin.top.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginIndex(){
        return "/user/login";
    }

    @GetMapping("/register")
    public String registerIndex(){
        return "/user/register";
    }

    /**
     * 注册
     * @param tbUser
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public String register(TbUser tbUser){
        Result result = userService.saveUser(tbUser);
        return JSON.toJSONString(result);
    }

    /**
     * 根据帐号密码登录
     * @param account
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestParam("username") String account, String password, HttpServletRequest request,
                        HttpServletResponse response){
        Result result = userService.findUserByAccountAndPssword(account,password);
        if(result.getStatus()==200){
            String userToken = Constant.REDIS_LOGIN+ UUID.randomUUID();
            redisTemplate.opsForValue().set(userToken,JSON.toJSONString(result.getData()),Constant.REDIS_LOGIN_TIME, TimeUnit.SECONDS);
            CookieUtils.setCookie(request,response,Constant.COOKIE_LOGIN,userToken,Constant.COOKIE_LOGIN_TIME);
        }
        result.setData(null);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @GetMapping("/check/{username}/1")
    public String checkUsername(@PathVariable("username") String username){
        Result result = userService.checkUsername(username);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @GetMapping("/check/{phone}/2")
    public String checkPhone(@PathVariable("phone") String phone){
        Result result = userService.checkPhone(phone);
        return JSON.toJSONString(result);
    }
}
