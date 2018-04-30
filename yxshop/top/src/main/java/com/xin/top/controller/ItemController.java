package com.xin.top.controller;

import com.alibaba.fastjson.JSON;
import com.xin.top.dto.*;
import com.xin.top.model.TbItem;
import com.xin.top.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/item")
@Controller
public class ItemController extends BaseController{
    @Autowired
    private ItemService itemService;

    @GetMapping("/{id}")
    public String findItem(@PathVariable long id, Model model,HttpServletRequest request){
        Result result = itemService.findItemByPrimaryKey(id);
        Result desc = itemService.findItemDescByPrimaryKey(id);
        model.addAttribute("username",getUserName(request));
        model.addAttribute("item",result.getData());
        model.addAttribute("itemDesc",desc.getData());
        return "item";
    }
}
