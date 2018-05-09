package com.xin.top.mapper;

import com.xin.top.model.TbOrder;
import org.springframework.stereotype.Component;

@Component
public interface TbOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);
}