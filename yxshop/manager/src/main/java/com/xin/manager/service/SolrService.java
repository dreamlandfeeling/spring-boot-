package com.xin.manager.service;

import com.xin.manager.dto.Result;
import com.xin.manager.model.TbItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SolrService {
    public Result add() throws IOException, SolrServerException;

    void addDoc(TbItem item) throws IOException, SolrServerException;

    public Result delete(String id) throws IOException, SolrServerException;

    public Result batchDelete(Long[] ids) throws IOException, SolrServerException;

    public Result deleteAll() throws IOException, SolrServerException;

    public Result getById(String id) throws Exception;

    public Result search() throws IOException, SolrServerException;

}
