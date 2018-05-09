package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.model.TbUser;
import com.xin.top.model.TbUserShipping;
import com.xin.top.service.UserService;
import com.xin.top.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        String phone = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        Result result;
        if(account.matches(phone)){
            result = userService.findUserByPhoneAndPssword(account,password);
        }else if (account.matches(email)){
            result = userService.findUserByEmailAndPssword(account,password);
        }else{
            result = userService.findUserByUsernameAndPssword(account,password);
        }
        if(result.getStatus()==200){
            String userToken = Constant.REDIS_LOGIN+ UUID.randomUUID();
            redisTemplate.opsForValue().set(userToken,JSON.toJSONString(result.getData()),Constant.REDIS_LOGIN_TIME, TimeUnit.SECONDS);
            CookieUtils.setCookie(request,response,Constant.COOKIE_LOGIN,userToken,Constant.COOKIE_LOGIN_TIME);
        }
        result.setData(null);
        return JSON.toJSONString(result);
    }

    @GetMapping("layout")
    public String layout(HttpServletRequest request,HttpServletResponse response){
        CookieUtils.deleteCookie(request,response, Constant.COOKIE_LOGIN);
        return "redirect:/user/login";
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


    @ResponseBody
    @GetMapping("/address/{id}")
    public String findAddress(@PathVariable Long id){
        Result result = userService.findUserShippingAddress(id);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @PostMapping("/address")
    public String addAddress(TbUserShipping userShipping,HttpServletRequest request){
        TbUser user = getUser(request);
        userShipping.setUserId(user.getId());
        Result result = userService.addUserShippingAddress(userShipping);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @PutMapping("/address/{id}")
    public String updateAddress(TbUserShipping userShipping,HttpServletRequest request){
        TbUser user = getUser(request);
        userShipping.setUserId(user.getId());
        Result result = userService.updateUserShippingAddress(userShipping);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @DeleteMapping("/address/{id}")
    public String deleteAddress(@PathVariable Long id){
        Result result = userService.deleteUserShippingAddress(id);
        return JSON.toJSONString(result);
    }
}
