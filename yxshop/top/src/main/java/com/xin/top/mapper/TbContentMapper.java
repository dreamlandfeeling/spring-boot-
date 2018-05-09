package com.xin.top.mapper;

import com.xin.top.model.TbContent;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TbContentMapper {
    List<TbContent> findContentByCategoryId(Long category_Id);

    long findContentNum();

    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);
}