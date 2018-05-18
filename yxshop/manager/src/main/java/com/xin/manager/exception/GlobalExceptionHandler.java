package com.xin.manager.exception;

import com.alibaba.fastjson.JSONObject;
import com.xin.manager.dto.ResultFactory;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "/exception/error";

    @ExceptionHandler(value = AuthorizationException.class)
    public Object defaultExceptionHandler(HttpServletRequest request,Exception e){
        return ResultFactory.getFailResult(401	,"权限不足");
    }

    //public static boolean isAjax(HttpServletRequest request){
    //    String header = request.getHeader("X-Requested-With");
    //    return header !=null && "XMLHttpRequest".equals(header);
    //}
}
