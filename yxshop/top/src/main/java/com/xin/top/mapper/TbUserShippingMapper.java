package com.xin.top.mapper;

import com.xin.top.model.TbUserShipping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbUserShippingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUserShipping record);

    int insertSelective(TbUserShipping record);

    TbUserShipping selectByPrimaryKey(Long id);

    List<TbUserShipping> selectByUserId(Long id);

    int updateByPrimaryKeySelective(TbUserShipping record);

    int updateByPrimaryKey(TbUserShipping record);
}