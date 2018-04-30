package com.xin.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.xin.manager.dto.EasyUITree;
import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.ResultFactory;
import com.xin.manager.mapper.TbItemCatMapper;
import com.xin.manager.mapper.TbItemDescMapper;
import com.xin.manager.mapper.TbItemMapper;
import com.xin.manager.model.TbItem;
import com.xin.manager.model.TbItemCat;
import com.xin.manager.model.TbItemDesc;
import com.xin.manager.service.ItemService;
import com.xin.manager.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private SolrService solrService;

    @Transactional
    @Override
    public Result insert(TbItem tbItem,String desc) {
        long id = tbItemMapper.selectMaxItemId()+1;
        tbItem.setId(id);
        tbItem.setStatus(TbItem.NORMAL);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItemMapper.insert(tbItem);
        insertItemDesc(id,desc);
        try {
            solrService.addDoc(tbItem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result insertItemDesc(Long id,String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        return ResultFactory.getSuccessResult();
    }

    @Transactional
    @Override
    public Result update(TbItem tbItem) {
        tbItem.setUpdated(new Date());
        tbItemMapper.updateByPrimaryKey(tbItem);
        try {
            solrService.addDoc(tbItem);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result updateToChanged(TbItem tbItem) {
        tbItemMapper.updateToChanged(tbItem);
        return ResultFactory.getSuccessResult();
    }
    @Transactional
    @Override
    public Result delete(long id) {
        tbItemMapper.deleteByPrimaryKey(id);
        tbItemDescMapper.deleteByPrimaryKey(id);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public PageBean findItemByPage(Map<String,Object> params) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        int page = Integer.parseInt(params.get("page").toString());
        int rows = Integer.parseInt(params.get("rows").toString());
        PageHelper.startPage(page, rows);

        List<TbItem> allItems = tbItemMapper.findByParams(params);        //全部商品
        long countNums = tbItemMapper.countItemByParams(params);            //总记录数
        PageBean<TbItem> pageData = new PageBean<>(page, rows, (int)countNums);
        pageData.setRows(allItems);
        return pageData;
    }
    //@Override
    //public PageBean findItemByPage(int page, int rows) {
    //    //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
    //    PageHelper.startPage(page, rows);
    //
    //    List<TbItem> allItems = tbItemMapper.findAll();        //全部商品
    //    long countNums = tbItemMapper.countItem();            //总记录数
    //    PageBean<TbItem> pageData = new PageBean<>(page, rows, (int)countNums);
    //    pageData.setRows(allItems);
    //    return pageData;
    //}
    @Transactional
    @Override
    public Result batchDelete(Long[] ids) {
        tbItemMapper.batchDeleteByPrimaryKey(ids);
        tbItemDescMapper.batchDeleteByPrimaryKey(ids);
        try {
            solrService.batchDelete(ids);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result findItemDescByPrimaryKey(Long id) {
        TbItemDesc desc = tbItemDescMapper.findItemDescByPrimaryKey(id);
        if(desc == null){
            ResultFactory.getFailResult(400,"商品详细未填写");
        }
        return ResultFactory.getSuccessResult(desc);
    }

    @Override
    public Result listItemCat() {
        long parentId = 0;//一级树的父id
        List<TbItemCat> list = tbItemCatMapper.selectItemCat();
        ArrayList<EasyUITree> trees = new ArrayList<EasyUITree>(list.size());
        //查询
        for (TbItemCat cat : list) {
            if (cat.getParentId().longValue() == parentId) {
                EasyUITree node = createTreeNode(cat);
                trees.add(node);
            }
        }
        for (EasyUITree tree : trees) {
            addTree(tree, list);
        }
        return ResultFactory.getSuccessResult(trees);
    }

    /**
     * 为当前节点添加子节点
     * @param tree  当前节点
     * @param catList   所有商品类
     */
    public void addTree(EasyUITree tree, List<TbItemCat> catList) {
        for (TbItemCat cat : catList) {
            if(cat.getParentId().longValue()==tree.getId().longValue()){
                if(tree.getChildren()==null){
                    tree.setState(EasyUITree.STATE_CLOSED);
                    tree.setChildren(new ArrayList<EasyUITree>());
                }
                EasyUITree node = createTreeNode(cat);
                if(cat.getIsParent()){
                    addTree(node,catList);
                }
                tree.getChildren().add(node);
            }
        }
    }

    public EasyUITree createTreeNode(TbItemCat cat){
        EasyUITree node = new EasyUITree();
        node.setId(cat.getId());
        node.setText(cat.getName());
        return node;
    }
}
