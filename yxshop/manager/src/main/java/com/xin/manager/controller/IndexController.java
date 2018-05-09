package com.xin.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    /**
     * 后台首页
     * @return
     */
    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }



    @RequestMapping(value="/content/{page}",method = RequestMethod.GET)
    public String contentIndex(@PathVariable String page){
        return "/content/"+page;
    }
    @RequestMapping(value="/item/{page}",method = RequestMethod.GET)
    public String itemIndex(@PathVariable String page){
        return "/item/"+page;
    }
}
