package com.xin.manager.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor{
    private Logger log = LogManager.getLogger(this.getClass());

    /**
     * 处理器实际执行 之前 会被执行 preHandle 当方法返回 true时，处理器链会继续执行；若方法返回 false，
     * DispatcherServlet即认为拦截器自身已经完成了对请求的处理（比如说，已经渲染了一个合适的视图），那么其余的拦截器以及执行链中的其他处理器就不会再被执行了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        StringBuffer url = request.getRequestURL();
        int index = url.indexOf("?_=");
        if(index>=0){
            String newUrl = url.substring(0,index);
            response.sendRedirect(newUrl);
            return false;
        }
        System.err.println("123");
        //response.setContentType("charset=utf-8");
        //response.setCharacterEncoding("UTF-8");
        return true;
    }

    /**
     * 处理器执行 完毕 以后被执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 整个请求处理完成 之后被执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
