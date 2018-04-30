package com.xin.top.service.impl;

import com.github.pagehelper.PageHelper;
import com.sun.xml.internal.txw2.output.ResultFactory;
import com.xin.top.dto.Result;
import com.xin.top.mapper.TbItemCatMapper;
import com.xin.top.mapper.TbItemDescMapper;
import com.xin.top.mapper.TbItemMapper;
import com.xin.top.model.TbItem;
import com.xin.top.model.TbItemCat;
import com.xin.top.model.TbItemDesc;
import com.xin.top.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;



    @Override
    public Result findItemByPrimaryKey(Long id) {
        TbItem item = tbItemMapper.selectByPrimaryKey(id);
        String image = item.getImage();
        String[] images = image.split(",");
        item.setImages(images);
        return Result.ok(item);
    }

    @Override
    public Result findItemByPrimaryKeyToCart(Long id,int num) {
        Result result = findItemByPrimaryKey(id);
        TbItem item = (TbItem)result.getData();
        item.setNum(num);
        item.setPrice(item.getPrice()/100);
        return Result.ok(item);
    }

    @Override
    public Result findItemDescByPrimaryKey(Long id) {
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        return Result.ok(itemDesc);
    }

}
