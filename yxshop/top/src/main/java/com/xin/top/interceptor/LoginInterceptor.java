package com.xin.top.interceptor;

import com.xin.top.dto.Constant;
import com.xin.top.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String redirect = request.getHeader("Referer");
        if(cookies==null){
            response.sendRedirect("/user/login?redirect="+redirect);
            return false;
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(Constant.COOKIE_LOGIN)){
                String token = cookie.getValue();
                if(!StringUtils.isNotBlank(token)){
                    response.sendRedirect("/user/login?redirect="+redirect);
                    return false;
                }
                String uesr = redisTemplate.opsForValue().get(token);
                if(!StringUtils.isNotBlank(uesr)){
                    response.sendRedirect("/user/login?redirect="+redirect);
                    return false;
                }
            }
        }
        return true;
    }
}
