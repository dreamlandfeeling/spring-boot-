package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.Constant;
import com.xin.top.model.TbUser;
import com.xin.top.utils.CookieUtils;
import com.xin.top.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

public class BaseController {

    @Autowired
    protected StringRedisTemplate redisTemplate;

    public TbUser getUser(HttpServletRequest request){
        String userToken = CookieUtils.getCookieValue(request, Constant.COOKIE_LOGIN);
        if(!StringUtils.isNotBlank(userToken)){
            return null;
        }
        String user = redisTemplate.opsForValue().get(userToken);
        if(!StringUtils.isNotBlank(user)){
            return null;
        }
        //redisTemplate.expire(userToken,Constant.REDIS_LOGIN_TIME, TimeUnit.SECONDS);
        TbUser tbuser = JSON.parseObject(user,TbUser.class);
        return tbuser;
    }

    public String getUserName(HttpServletRequest request){
        TbUser user = getUser(request);
        if(user==null){
            return null;
        }
        return user.getUsername();
    }
}
