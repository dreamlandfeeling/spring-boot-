package com.xin.manager.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(urlPatterns = "/**",filterName = "URLFilter")
//public class URLFilter implements Filter{
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request= (HttpServletRequest)servletRequest;
//        HttpServletResponse response= (HttpServletResponse)servletRequest;
//        StringBuffer url = request.getRequestURL();
//        System.err.println(url.toString());
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
