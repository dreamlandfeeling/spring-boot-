package com.xin.top.controller;

import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.model.TbUser;
import com.xin.top.service.ContentService;
import com.xin.top.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController extends BaseController{
    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request){
        Result result = contentService.findAll();
        Result num = contentService.findContentNum();
        model.addAttribute("username",getUserName(request));
        model.addAttribute("contents",result.getData());
        model.addAttribute("contentNum",num.getData());
        return "index";
    }
}

