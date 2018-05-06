package com.xin.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.ResultFactory;
import com.xin.manager.mapper.TbItemCatMapper;
import com.xin.manager.mapper.TbItemMapper;
import com.xin.manager.model.TbItem;
import com.xin.manager.model.TbItemCat;
import com.xin.manager.service.SolrService;
import com.xin.manager.utils.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService{

    @Autowired
    private SolrClient solrClient;
    @Autowired
    private StringRedisTemplate redis;
    
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    private final String ITEM_SOLR_COUNT = "item_solr_count";
    @Override
    public Result add() throws IOException, SolrServerException {
        String count = redis.opsForValue().get(ITEM_SOLR_COUNT);
        if(StringUtils.isNotBlank(count)){
            List<TbItem> newItemList = tbItemMapper.findNewItem(Long.valueOf(count));
            addDoc(newItemList);
            return ResultFactory.getSuccessResult();
        }
        List<TbItem> list = tbItemMapper.findAll();
        addDoc(list);
        return ResultFactory.getSuccessResult();
    }

    /**
     * 将集合中的元素添加到solr
     * @param list
     * @throws IOException
     * @throws SolrServerException
     */
    private void addDoc(List<TbItem> list) throws IOException, SolrServerException {
        if(list==null||list.size()<=0){
            return;
        }
        SolrInputDocument doc = new SolrInputDocument();
        for (TbItem item : list) {
            doc.setField("id",item.getId());
            doc.setField("item_title",item.getTitle());
            doc.setField("item_sell_point",item.getSellPoint());
            doc.setField("item_price",item.getPrice());
            doc.setField("item_image",item.getImage());
            TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(item.getCid());
            doc.setField("item_category_name",itemCat.getName());
            solrClient.add(doc);
        }
        redis.opsForValue().set(ITEM_SOLR_COUNT, list.get(list.size()-1).getId().toString());
        solrClient.commit();
    }
    @Override
    public void addDoc(TbItem item) throws IOException, SolrServerException {
        if(item==null){
            return;
        }
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id",item.getId());
        doc.setField("item_title",item.getTitle());
        doc.setField("item_sell_point",item.getSellPoint());
        doc.setField("item_price",item.getPrice());
        doc.setField("item_image",item.getImage());
        TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(item.getCid());
        doc.setField("item_category_name",itemCat.getName());
        solrClient.add(doc);
        solrClient.commit();
    }

    @Override
    public Result addOrDeleteByItem(TbItem item) throws IOException, SolrServerException {
        if(item.getStatus()==TbItem.NORMAL){
            addDoc(item);
        }else {
            delete(item.getId().toString());
        }
        solrClient.commit();
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result delete(String id) throws IOException, SolrServerException {
            solrClient.deleteById(id);
            solrClient.commit();
        return ResultFactory.getSuccessResult();
    }

    @Override
    public Result batchDelete(Long[] ids) throws IOException, SolrServerException {
        List<String> list = new ArrayList<>();
        for (long id : ids) {
            list.add(String.valueOf(id));
        }
        solrClient.deleteById(list);
        solrClient.commit();
        return ResultFactory.getSuccessResult();
    }


    @Override
    public Result deleteAll() throws IOException, SolrServerException {
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        return ResultFactory.getSuccessResult();
    }
    @Override
    public Result getById(String id) throws Exception {
        SolrDocument document = solrClient.getById(id);
        return ResultFactory.getSuccessResult();
    }

    @Override
    public void status() {

    }

}

