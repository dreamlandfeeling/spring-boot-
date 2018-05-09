package com.xin.top.service.impl;

import com.xin.top.dto.OrderInfo;
import com.xin.top.dto.Result;
import com.xin.top.mapper.TbOrderItemMapper;
import com.xin.top.mapper.TbOrderMapper;
import com.xin.top.mapper.TbOrderShippingMapper;
import com.xin.top.mapper.TbUserShippingMapper;
import com.xin.top.model.TbOrder;
import com.xin.top.model.TbOrderItem;
import com.xin.top.model.TbOrderShipping;
import com.xin.top.model.TbUserShipping;
import com.xin.top.service.OrderService;
import com.xin.top.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private TbUserShippingMapper tbUserShippingMapper;
    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;
    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public Result findUserAddress(Long id) {
        List<TbUserShipping> shippingList = tbUserShippingMapper.selectByUserId(id);
        return Result.ok(shippingList);
    }

    @Transactional
    @Override
    public Result createOrder(OrderInfo orderInfo) {
        TbOrder order = orderInfo.getOrder();
        Long addrId = orderInfo.getAddrId();
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        complementOrder(order);
        complementOrderItems(orderItems,order.getOrderId());
        TbOrderShipping orderShipping = createOrderShipping(order.getOrderId(), addrId);
        tbOrderMapper.insert(order);
        tbOrderItemMapper.insertByBatch(orderItems);
        tbOrderShippingMapper.insert(orderShipping);
        return Result.ok(order);
    }

    public void complementOrder(TbOrder order){
        String orderId = IdUtils.createId();
        order.setOrderId(orderId);
        order.setStatus(TbOrder.PAYMENT_NO_PAY);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
    }

    public void complementOrderItems(List<TbOrderItem> orderItems,String orderId){
        for (TbOrderItem orderItem : orderItems) {
            orderItem.setId(IdUtils.createId());
            orderItem.setOrderId(orderId);
        }
    }

    public TbOrderShipping createOrderShipping(String orderId,Long addrId){
        TbOrderShipping ordershipping = new TbOrderShipping();
        TbUserShipping userShipping = tbUserShippingMapper.selectByPrimaryKey(addrId);
        ordershipping.setOrderId(orderId);
        ordershipping.setReceiverName(userShipping.getName());
        ordershipping.setReceiverPhone(userShipping.getPhone());
        ordershipping.setReceiverState(userShipping.getProvince());
        ordershipping.setReceiverCity(userShipping.getCity());
        ordershipping.setReceiverDistrict(userShipping.getDistrict());
        ordershipping.setReceiverAddress(userShipping.getAddress());
        ordershipping.setReceiverZip(userShipping.getZipCode());
        ordershipping.setCreated(new Date());
        ordershipping.setUpdated(new Date());
        return ordershipping;
    }
}
