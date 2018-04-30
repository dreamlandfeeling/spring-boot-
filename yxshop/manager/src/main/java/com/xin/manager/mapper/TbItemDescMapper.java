package com.xin.manager.mapper;

import com.xin.manager.model.TbItemDesc;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
public interface TbItemDescMapper {
    @Results({
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "itemDesc", column = "item_desc"),
    })
    @Select("select * from tb_item_desc where item_id = #{id}")
    TbItemDesc findItemDescByPrimaryKey(Long id);

    @Insert("insert into tb_item_desc() values(#{itemId},#{itemDesc},#{created},#{updated})")
    int insert(TbItemDesc tbItemDesc);

    @Select("select Max(item_id) from tb_item_desc")
    long findMaxId();

    @Delete("delete from tb_item_desc where item_id = #{id}")
    int deleteByPrimaryKey(Long id);

    @Delete("<script>" +
            " delete from tb_item_desc where item_id in " +
            "<foreach item='id' collection='array' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDeleteByPrimaryKey(Long[] ids);
}