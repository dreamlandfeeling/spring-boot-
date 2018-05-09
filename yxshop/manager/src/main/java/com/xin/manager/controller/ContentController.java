package com.xin.manager.controller;

import com.alibaba.fastjson.JSON;
import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.model.TbContent;
import com.xin.manager.model.TbContentCategory;
import com.xin.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("content")
public class ContentController {
    @Autowired
    private ContentService contentService;


    /**
     * 商品列表
     * @param page 当前页
     * @param rows 显示多少行
     * @return 需要返回总条数和当前页的数据
     */
    @GetMapping("/")
    public String listContent(int page,int rows,long categoryId){
        PageBean pageBean = contentService.findContentByPage(page, rows,categoryId);
        return JSON.toJSONString(pageBean);
    }
    @GetMapping("cat")
    public String listContentCat(@RequestParam(defaultValue = "0") long id){
        Result result = contentService.findByParentId(id);
        return JSON.toJSONString(result.getData());
    }
    @PostMapping("/")
    public String insertContent(TbContent TbContent){
        Result result = contentService.insertContent(TbContent);
        return JSON.toJSONString(result);
    }
    @PostMapping("/cat/")
    public String insertContentCat(TbContentCategory tbContentCategory){
        Result result = contentService.insertContentCat(tbContentCategory);
        return JSON.toJSONString(result);
    }
    @PutMapping("/{id}")
    public String updateContentToChanged(TbContent tbContent){
        Result result = contentService.updateContentToChanged(tbContent);
        return JSON.toJSONString(result);
    }
    @PatchMapping("/cat/{id}")
    public String updateContentCatToChanged(TbContentCategory tbContentCategory){
        Result result = contentService.updateContentCatToChanged(tbContentCategory);
        return JSON.toJSONString(result);
    }

    @DeleteMapping("/{id}")
    public String deleteContent(@PathVariable("id") long id){
        Result result = contentService.deleteContent(id);
        return JSON.toJSONString(result);
    }

    @DeleteMapping("/cat/{id}")
    public String deleteContentCat(@PathVariable("id") long id){
        Result result = contentService.deleteContentCat(id);
        return JSON.toJSONString(result);
    }


}
