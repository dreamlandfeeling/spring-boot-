package com.xin.top.service;

import com.xin.top.dto.Result;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface SearchService {

    Result search(String keyword,int page)throws IOException, SolrServerException;
}

