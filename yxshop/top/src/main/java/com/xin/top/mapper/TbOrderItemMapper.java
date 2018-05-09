package com.xin.top.mapper;

import com.xin.top.model.TbOrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbOrderItemMapper {
    int deleteByPrimaryKey(String id);

    int insertByBatch(List<TbOrderItem> record);

    int insert(TbOrderItem record);

    int insertSelective(TbOrderItem record);

    TbOrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbOrderItem record);

    int updateByPrimaryKey(TbOrderItem record);
}