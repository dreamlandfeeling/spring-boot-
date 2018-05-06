package com.xin.manager.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.pagehelper.PageInfo;
import com.sun.tracing.dtrace.ModuleAttributes;
import com.xin.manager.dto.*;
import com.xin.manager.model.TbItem;
import com.xin.manager.service.ItemService;
import com.xin.manager.utils.FileUtils;
import com.xin.manager.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/item")
@RestController
public class ItemController {
    @Value("${IMG.UPLOAD.PATH}")
    private String filePath;
    @Value("${IMG.LOAD.PATH}")
    private String fileLoadPath;
    @Autowired
    private ItemService itemService;

    /**
     * 商品列表
     * @param params 查询参数其中一定有page和rows
     * @return 需要返回总条数和当前页的数据
     */
    @GetMapping("/")
    public String listItem(@RequestParam Map<String,Object> params){
        Query query = new Query(params);
        PageBean pageBean = itemService.findItemByPage(query);
        return JSON.toJSONString(pageBean);
    }

    //@GetMapping("/")
    //public String listItem(int page,int rows){
    //    PageBean pageBean = itemService.findContentByPage(page, rows);
    //    return JSON.toJSONString(pageBean);
    //}

    /**
     * 商品类别列表
     * @return
     */
    @GetMapping("/cat")
    public String itemCategory(){
        Result result = itemService.listItemCat();
        return JSON.toJSONString(result.getData());
    }

    /**
     * 商品详情
     * @return
     */
    @GetMapping("/desc/{id}")
    public String findItemDesc(@PathVariable("id") Long id ){
        Result result = itemService.findItemDescByPrimaryKey(id);
        return JSON.toJSONString(result);
    }

    @PostMapping("/")
    public String insertItem(TbItem tbItem,String desc){
        Result result = itemService.insert(tbItem,desc);
        return JSON.toJSONString(result);
    }
    /**
     * 图片上传
     * @param file 图片
     * @param request
     * @return 上传结果
     * @throws Exception
     */
    @PostMapping("/pic")
    public String uploadImg(@RequestParam("uploadFile") MultipartFile file, HttpServletRequest request) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = UUID.randomUUID()+fileName.substring(fileName.indexOf("."));
        byte[] bytes = file.getBytes();
        //String savePath = this.getClass().getClassLoader().getResource("static"+filePath).getPath();
        FileUtils.saveFile(bytes,fileName,filePath+fileLoadPath);
        UploadResult result = UploadResult.ok(fileLoadPath+fileName);
        return JSON.toJSONString(result);
    }


    /**
     * 更新全部
     * @param tbItem
     * @return
     */
    @PutMapping("/{id}")
    public String updateItem(TbItem tbItem){
        Result result = itemService.update(tbItem);
        return JSON.toJSONString(result);
    }
    /**
     * 更新部分
     * @param tbItem
     * @return
     */
    @PatchMapping("/{id}")
    public String updateToChanged(TbItem tbItem){
        Result result = itemService.updateToChanged(tbItem);
        return JSON.toJSONString(result);
    }


    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") long id){
        Result result = itemService.delete(id);
        return JSON.toJSONString(result);
    }
    @DeleteMapping("/batch")
    public String deleteItems(@RequestBody String ids){
        ids = StringUtils.resolveJson(ids);
        System.err.println(ids);
        String[] split = ids.split("%2C");
        Long[] longs = new Long[split.length];
        for (int i = 0; i < split.length; i++) {
            longs[i] = Long.valueOf(split[i]);
            System.err.println(longs[i]);
        }
        Result result = itemService.batchDelete(longs);
        return JSON.toJSONString(result);
    }
}
