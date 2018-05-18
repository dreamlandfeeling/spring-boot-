package com.xin.manager.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Component
public class FreemarkerComponent {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}