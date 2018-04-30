package com.xin.manager.controller;

import com.alibaba.fastjson.JSON;
import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.UploadResult;
import com.xin.manager.model.TbItemParam;
import com.xin.manager.service.ItemParamService;
import com.xin.manager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 此功能暂未开发  废弃了
 */
@RequestMapping("/item/param")
@RestController
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 商品列表
     * @param page 当前页
     * @param rows 显示多少行
     * @return 需要返回总条数和当前页的数据
     */
    @GetMapping("/")
    public String listItem(int page,int rows){
        PageBean pageBean = itemParamService.findItemByPage(page, rows);
        return JSON.toJSONString(pageBean);
    }

    @PostMapping("/")
    public String insertItem(@ModelAttribute TbItemParam tbItemParam){
        Result result = itemParamService.insert(tbItemParam);
        return JSON.toJSONString(result);
    }


    /**
     * 更新全部
     * @param tbItemParam
     * @return
     */
    @PutMapping("/{id}")
    public String updateItem(TbItemParam tbItemParam){
        Result result = itemParamService.update(tbItemParam);
        return JSON.toJSONString(result);
    }
    /**
     * 更新部分
     * @param tbItemParam
     * @return
     */
    @PatchMapping("/{id}")
    public String updateToChanged(TbItemParam tbItemParam){
        Result result = itemParamService.updateToChanged(tbItemParam);
        return JSON.toJSONString(result);
    }


    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") long id){
        Result result = itemParamService.delete(id);
        return JSON.toJSONString(result);
    }
}
