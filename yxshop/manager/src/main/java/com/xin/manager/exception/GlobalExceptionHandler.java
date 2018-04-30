package com.xin.manager.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String DEFAULT_ERROR_VIEW = "/exception/error";

    //@ExceptionHandler(value = Exception.class)
    //public Object defaultExceptionHandler(HttpServletRequest request,Exception e){
    //    if(isAjax(request)){
    //        JSONObject object = new JSONObject();
    //        object.put("url",request.getRequestURL());
    //        object.put("message",e.getMessage());
    //        return object;
    //    }else{
    //        ModelAndView model = new ModelAndView();
    //        model.addObject("url",request.getRequestURL());
    //        model.addObject("message",e.getMessage());
    //        model.setViewName("/error");
    //        return model;
    //    }
    //}

    public static boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        return header !=null && "XMLHttpRequest".equals(header);
    }
}
