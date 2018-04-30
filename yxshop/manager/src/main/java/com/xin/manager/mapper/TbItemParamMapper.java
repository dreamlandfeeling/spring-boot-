package com.xin.manager.mapper;

import com.xin.manager.model.TbItemParam;
import com.xin.manager.model.TbItemParam;
import com.xin.manager.sql.ItemSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbItemParamMapper {
    @Result(property = "sellPoint",column = "sell_point")
    @Update("update tb_item_param SET title = #{title},sell_point = #{sellPoint},price = #{price},num = #{num}," +
            "barcode = #{barcode},image = #{image},updated=#{updated} where id = #{id}")
    int updateByPrimaryKey(TbItemParam TbItemParam);

    @UpdateProvider(type = ItemSqlProvider.class,method = "update")
    int updateToChanged(TbItemParam TbItemParam);

    @Delete("delete from tb_item_param where id = #{id}")
    int deleteByPrimaryKey(Long id);


    @Result(property = "sellPoint",column = "sell_point")
    @Insert("insert into tb_item_param() values(#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{status}," +
            "#{created},#{updated})")
    int insert(TbItemParam item);

    @Select("select MAX(id) from tb_item_param")
    long selectMaxItemId();
    @Results({
            @Result(property = "itemCatId",column = "item_cat_id"),
            @Result(property = "paramData",column = "param_data"),
    })
    @Select("select * from tb_item_param")
    List<TbItemParam> findAll();

    @Select("select count(*) from tb_item_param")
    long countItem();

}