package com.xin.top.dto;

import java.io.Serializable;

public class SearchResult<T> implements Serializable{
    /**
     * 查询内容
     */
    private String query;
    private int page;
    private int totalPages;
    /**
     * 商品总数
     */
    private int recourdCount;

    private T data;


    public SearchResult(String query, int page, int totalPages, int recourdCount, T data) {
        this.query = query;
        this.page = page;
        this.totalPages = totalPages;
        this.recourdCount = recourdCount;
        this.data = data;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getRecourdCount() {
        return recourdCount;
    }

    public void setRecourdCount(int recourdCount) {
        this.recourdCount = recourdCount;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "query='" + query + '\'' +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", recourdCount=" + recourdCount +
                ", data=" + data +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
