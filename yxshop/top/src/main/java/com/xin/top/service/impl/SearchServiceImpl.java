package com.xin.top.service.impl;

import com.sun.xml.internal.txw2.output.ResultFactory;
import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.dto.SearchResult;
import com.xin.top.model.TbItem;
import com.xin.top.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SOLR.SEARCH.FIELD}")
    private String DEFAULT_SEARCH;
    @Autowired
    private SolrClient solrClient;



    public Result search(String keyword,int page) throws IOException, SolrServerException {
        SolrQuery params = new SolrQuery();
        //查询条件, 这里的 q 对应 下面图片标红的地方
        params.set("q", keyword);
        //过滤条件
        //params.set("fq", "product_price:[100 TO 100000]");
        //排序
        //params.addSort("product_price", SolrQuery.ORDER.asc);
        //分页
        params.setStart((page-1)* Constant.SOLR_ROW);
        params.setRows(Constant.SOLR_ROW);
        //默认域
        params.set("df", DEFAULT_SEARCH);
        ////只查询指定域
        //params.set("fl", "id,product_title,product_price");
        params.setHighlight(true);
        //指定高亮域
        params.addHighlightField("item_title");
        params.addHighlightField("item_sell_point");
        //设置前缀
        params.setHighlightSimplePre("<span style='color:red'>");
        //设置后缀
        params.setHighlightSimplePost("</span>");

        QueryResponse queryResponse = solrClient.query(params);
        SolrDocumentList results = queryResponse.getResults();
        int numFound = (int)results.getNumFound();
        System.out.println(numFound);
        //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
        Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();
        ArrayList<TbItem> itemList = new ArrayList<>();
        for (SolrDocument result : results) {
            TbItem tbItem = new TbItem();
            tbItem.setId(Long.valueOf(result.get("id").toString()));
            tbItem.setPrice(Long.valueOf(result.get("item_price").toString()));
            String imagesStr = result.get("item_image").toString();
            String[] images = imagesStr.split(",");
            tbItem.setImages(images);

            Map<String, List<String>> map = highlight.get(result.get("id"));
            List<String> item_title = map.get("item_title");
            List<String> item_sell_point = map.get("item_sell_point");
            if(item_title!=null&&item_title.size()>0){
                tbItem.setTitle(item_title.get(0));
            }else{
                tbItem.setTitle(result.get("item_title").toString());
            }
            if(item_sell_point!=null&&item_sell_point.size()>0){
                tbItem.setSellPoint(item_sell_point.get(0));
            }else{
                tbItem.setSellPoint(result.get("item_sell_point").toString());
            }
            itemList.add(tbItem);
        }
        int pageTotal = numFound % 20>0?(numFound/20)+1:numFound/20;
        SearchResult<ArrayList<TbItem>> result = new SearchResult<>(keyword, page,pageTotal, numFound, itemList);
        solrClient.commit();
        return Result.ok(result);
    }
}
