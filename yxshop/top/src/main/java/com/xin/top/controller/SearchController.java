package com.xin.top.controller;

import com.xin.top.dto.Result;
import com.xin.top.dto.SearchResult;
import com.xin.top.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/search")
public class SearchController extends BaseController{
    @Autowired
    private SearchService searchService;
    @GetMapping("/")
    public String search(String keyword, @RequestParam(defaultValue = "1") int page, Model model) throws IOException, SolrServerException {
        Result result = searchService.search(keyword,page);
        SearchResult search = (SearchResult) result.getData();
        model.addAttribute("keyword",keyword);
        model.addAttribute("search",search);
        model.addAttribute("itemList",search.getData());
        return "search";
    }
}
