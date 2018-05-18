package com.xin.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.xin.manager.dto.EasyUITree;
import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.ResultFactory;
import com.xin.manager.mapper.TbItemParamMapper;
import com.xin.manager.model.TbItemParam;
import com.xin.manager.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public PageBean findItemByPage(int page, int rows) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(page, rows);

        List<TbItemParam> allItems = tbItemParamMapper.findAll();        //全部商品
        long countNums = tbItemParamMapper.countItem();            //总记录数
        PageBean<TbItemParam> pageData = new PageBean<>(page, rows, (int)countNums);
        pageData.setRows(allItems);
        return pageData;
    }


    @Transactional
    @Override
    public Result insert(TbItemParam TbItemParam) {
        long id = tbItemParamMapper.selectMaxItemId();
        TbItemParam.setId(id+1);
        TbItemParam.setCreated(new Date());
        TbItemParam.setUpdated(new Date());
        tbItemParamMapper.insert(TbItemParam);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result update(TbItemParam TbItemParam) {
        TbItemParam.setUpdated(new Date());
        tbItemParamMapper.updateByPrimaryKey(TbItemParam);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result updateToChanged(TbItemParam TbItemParam) {
        tbItemParamMapper.updateToChanged(TbItemParam);
        return ResultFactory.getSuccessResult();
    }


    @Override
    public Result delete(long id) {
        tbItemParamMapper.deleteByPrimaryKey(id);
        return ResultFactory.getSuccessResult();
    }




}
