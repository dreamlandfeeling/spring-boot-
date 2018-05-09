package com.xin.manager.dto;

import java.util.List;

public class PageResult<T> {
    private Integer total;
    private List<T> rows;

    public PageResult(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
