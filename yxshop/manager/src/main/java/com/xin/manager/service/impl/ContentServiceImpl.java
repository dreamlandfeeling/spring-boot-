package com.xin.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.xin.manager.dto.EasyUITree;
import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.ResultFactory;
import com.xin.manager.mapper.TbContentCategoryMapper;
import com.xin.manager.mapper.TbContentMapper;
import com.xin.manager.model.TbContent;
import com.xin.manager.model.TbContentCategory;
import com.xin.manager.model.TbItem;
import com.xin.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public Result findByParentId(long id) {
        List<TbContentCategory> list = tbContentCategoryMapper.findByParentId(id);
        List<EasyUITree> trees = new ArrayList<>();
        for (TbContentCategory cat : list) {
            EasyUITree tree = new EasyUITree();
            tree.setId(cat.getId());
            tree.setText(cat.getName());
            if(cat.getIsParent()){
                tree.setState(EasyUITree.STATE_CLOSED);
            }
            trees.add(tree);
        }
        return ResultFactory.getSuccessResult(trees);
    }

    @Override
    public Result insertContent(TbContent tbContent) {
        complementContent(tbContent);
        tbContentMapper.insertSelective(tbContent);
        return ResultFactory.getSuccessResult();
    }

    public void complementContent(TbContent tbContent) {
        tbContent.setId(tbContentMapper.findMaxId()+1);
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
    }

    @Transactional
    @Override
    public Result insertContentCat(TbContentCategory cat) {
        updateParentContentCat(cat);
        complementContentCat(cat);
        tbContentCategoryMapper.insertContentCat(cat);
        return ResultFactory.getSuccessResult(cat);
    }

    public void updateParentContentCat(TbContentCategory cat) {
        TbContentCategory parentCat = new TbContentCategory();
        parentCat.setId(cat.getParentId());
        parentCat.setIsParent(TbContentCategory.ISPARENT_TRUE);
        parentCat.setUpdated(new Date());
        tbContentCategoryMapper.updateToChanged(parentCat);
    }

    public void complementContentCat(TbContentCategory cat) {
        long id = tbContentCategoryMapper.findMaxContentCatId() + 1;
        cat.setId(id);
        cat.setStatus(TbContentCategory.STATUS_NORMAL);
        cat.setSortOrder(TbContentCategory.SORTORDER_DEFAULT);
        cat.setIsParent(TbContentCategory.ISPARENT_DEFAULT);
        cat.setCreated(new Date());
        cat.setUpdated(new Date());
    }


    @Override
    public Result updateContentToChanged(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateSelective(tbContent);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result updateContentCatToChanged(TbContentCategory tbContentCategory) {
        tbContentCategoryMapper.updateToChanged(tbContentCategory);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result deleteContent(long id) {
        tbContentMapper.deleteContent(id);
        return ResultFactory.getSuccessResult();
    }
    @Transactional
    @Override
    public Result deleteContentCat(long id) {
        TbContentCategory deleteContentCat = tbContentCategoryMapper.findByPrimaryKey(id);
        Long parentId = deleteContentCat.getParentId();
        tbContentCategoryMapper.deleteContentCat(id);
        List<TbContentCategory> children = tbContentCategoryMapper.findByParentId(deleteContentCat.getParentId());
        if(children.size()==0||children==null){
            TbContentCategory parentContentCat = tbContentCategoryMapper.findByPrimaryKey(parentId);
            parentContentCat.setIsParent(TbContentCategory.ISPARENT_DEFAULT);
            tbContentCategoryMapper.updateToChanged(parentContentCat);
        }
        return ResultFactory.getSuccessResult();
    }

    @Override
    public PageBean findContentByPage(int page, int rows, long categoryId) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(page, rows);

        List<TbContent> allItems = tbContentMapper.findAll(categoryId);        //全部商品
        long countNums = tbContentMapper.countContent();            //总记录数
        PageBean<TbContent> pageData = new PageBean<>(page, rows, (int)countNums);
        pageData.setRows(allItems);
        return pageData;
    }
}
