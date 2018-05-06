package com.xin.manager.service;

import com.xin.manager.dto.Result;
import com.xin.manager.model.TbItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SolrService {
    Result add() throws IOException, SolrServerException;

    void addDoc(TbItem item) throws IOException, SolrServerException;

    Result delete(String id) throws IOException, SolrServerException;

    Result batchDelete(Long[] ids) throws IOException, SolrServerException;

    Result deleteAll() throws IOException, SolrServerException;

    Result getById(String id) throws Exception;

    Result addOrDeleteByItem(TbItem item) throws IOException, SolrServerException;

    void status();
}
