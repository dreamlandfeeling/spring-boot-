package com.xin.manager.controller;

import com.alibaba.fastjson.JSON;
import com.xin.manager.dto.Result;
import com.xin.manager.dto.ResultFactory;
import com.xin.manager.service.SolrService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/solr")
public class SolrController {
    @Autowired
    private SolrService solrService;

    @PostMapping("/")
    public String add() throws IOException, SolrServerException {
        solrService.add();
        return JSON.toJSONString(ResultFactory.getSuccessResult());
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) throws IOException, SolrServerException {
        solrService.delete(id);
        return JSON.toJSONString(ResultFactory.getSuccessResult());
    }

    /**
     * 删除所有的索引
     * @return
     */
    @DeleteMapping("/")
    public String deleteAll() throws IOException, SolrServerException {
        solrService.deleteAll();
        return JSON.toJSONString(ResultFactory.getSuccessResult());
    }

    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") String id) throws Exception {
        solrService.getById(id);
        return JSON.toJSONString(ResultFactory.getSuccessResult());
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search")
    public String search() throws IOException, SolrServerException {
        Result search = solrService.search();
        return JSON.toJSONString(ResultFactory.getSuccessResult(search));
    }

}

