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

    @PostMapping("/status")
    public String status() throws IOException, SolrServerException {
        solrService.status();
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


}

