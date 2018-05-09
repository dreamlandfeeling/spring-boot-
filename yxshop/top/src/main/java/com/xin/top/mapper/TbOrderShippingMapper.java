package com.xin.top.mapper;

import com.xin.top.model.TbOrderShipping;
import org.springframework.stereotype.Component;

@Component
public interface TbOrderShippingMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TbOrderShipping record);

    int insertSelective(TbOrderShipping record);

    TbOrderShipping selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TbOrderShipping record);

    int updateByPrimaryKey(TbOrderShipping record);
}