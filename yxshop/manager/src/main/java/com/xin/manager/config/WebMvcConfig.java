package com.xin.manager.config;

import com.xin.manager.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;



//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/*")
//                .addResourceLocations("/META-INF/resources/")
//                .addResourceLocations("/resources/")
//                .addResourceLocations("/static/")
//                .addResourceLocations("/public/")
//                .addResourceLocations("file:/"+Constant.IMG_UPLOAD_PATH);
//    }
//}