package com.xin.top.service;

import com.xin.top.dto.OrderInfo;
import com.xin.top.dto.Result;
import com.xin.top.model.TbOrder;
import com.xin.top.model.TbOrderItem;

import java.util.List;

public interface OrderService {
    Result findUserAddress(Long id);

    Result createOrder(OrderInfo orderInfo);
}
