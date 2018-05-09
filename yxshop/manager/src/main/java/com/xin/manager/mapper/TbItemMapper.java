package com.xin.manager.mapper;

import com.xin.manager.model.TbItem;
import com.xin.manager.sql.ItemSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import redis.clients.jedis.params.Params;

import java.util.List;
import java.util.Map;

@Component
public interface TbItemMapper {
    @Select("select MAX(id) from tb_item")
    long selectMaxItemId();
    @Results({
            @Result(property = "sellPoint",column = "sell_point")
    })
    @Select("select * from tb_item where status = 1 and id > #{id}")
    List<TbItem> findNewItem(Long id );

    @Results({
            @Result(property = "sellPoint",column = "sell_point")
    })

    @Select("select * from tb_item where id = #{id}")
    TbItem findItemById(Long id);

    @Select("select * from tb_item where status = 1")
    List<TbItem> findAll();


    @Results({
            @Result(property = "sellPoint",column = "sell_point"),
            @Result(property = "cid",column = "cid"),
            @Result(property = "itemCat",column = "cid"
                    ,one = @One(select = "com.xin.manager.mapper.TbItemCatMapper.selectByPrimaryKey"))
    })
    @SelectProvider(type = ItemSqlProvider.class,method = "queryByParams")
    List<TbItem> findByParams(Map<String,Object> params);

    @Select("select count(*) from tb_item where status = 1")
    long countItem();
    @SelectProvider(type = ItemSqlProvider.class,method = "countItemByParams")
    long countItemByParams(Map<String,Object> params);

    @Result(property = "sellPoint",column = "sell_point")
    @Update("update tb_item SET title = #{title},sell_point = #{sellPoint},price = #{price},num = #{num}," +
            "barcode = #{barcode},image = #{image},updated=#{updated} where id = #{id}")
    int updateByPrimaryKey(TbItem tbItem);

    @UpdateProvider(type = ItemSqlProvider.class,method = "update")
    int updateToChanged(TbItem tbItem);

    @Delete("delete from tb_item where id = #{id}")
    int deleteByPrimaryKey(Long id);


    @Delete("<script>" +
            " delete from tb_item where id in " +
            "<foreach item='id' collection='array' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    int batchDeleteByPrimaryKey(Long[] ids);


    @Result(property = "sellPoint",column = "sell_point")
    @Insert("insert into tb_item() values(#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{status}," +
            "#{created},#{updated})")
    int insert(TbItem item);




}