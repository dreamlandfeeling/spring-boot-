package com.xin.top.service.impl;

import com.xin.top.dto.Result;
import com.xin.top.mapper.TbContentMapper;
import com.xin.top.model.TbContent;
import com.xin.top.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public Result findAll() {
        List<TbContent> list = tbContentMapper.findAll();
        return Result.ok(list);
    }

    @Override
    public Result findContentNum() {
        long num = tbContentMapper.findContentNum();
        return Result.ok(num);
    }
}
