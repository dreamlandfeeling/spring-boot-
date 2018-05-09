package com.xin.manager.mapper;

import com.xin.manager.model.TbItemCat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbItemCatMapper {
    @Results({
            @Result(property = "parentId",column = "parent_id"),
            @Result(property = "isParent",column = "is_parent")
    })
    @Select("select id,parent_id,name,is_parent from tb_item_cat where status=1 order by sort_order")
    List<TbItemCat> selectItemCat();

    @Select("select id,name from tb_item_cat where id = #{id}")
    TbItemCat selectByPrimaryKey(Long id);

}