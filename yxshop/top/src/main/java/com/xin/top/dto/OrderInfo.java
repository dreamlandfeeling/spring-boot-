package com.xin.top.dto;

import com.xin.top.model.TbOrder;
import com.xin.top.model.TbOrderItem;

import java.util.List;

public class OrderInfo {
    private Long addrId;
    private TbOrder order;
    private List<TbOrderItem> orderItems;


    public OrderInfo() {
        super();
    }
    public OrderInfo(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getAddrId() {
        return addrId;
    }

    public void setAddrId(Long addrId) {
        this.addrId = addrId;
    }

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }
}
