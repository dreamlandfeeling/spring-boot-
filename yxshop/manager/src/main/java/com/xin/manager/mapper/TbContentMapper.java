package com.xin.manager.mapper;

import com.xin.manager.model.TbContent;
import com.xin.manager.model.TbItem;
import com.xin.manager.sql.ContentSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TbContentMapper {
    @Results({
            @Result(property = "subTitle", column = "sub_title"),
            @Result(property = "titleDesc", column = "title_desc"),
            @Result(property = "categoryId", column = "category_id")
    })
    @Select("select * from tb_content where category_id = #{categoryId}")
    List<TbContent> findAll(long categoryId);
    @Select("select count(*) from tb_content")
    long countContent();

    @Select("select MAX(id) from tb_content")
    long findMaxId();

    @UpdateProvider(type = ContentSqlProvider.class,method = "update")
    int updateSelective(TbContent tbContent);

    @InsertProvider(type = ContentSqlProvider.class,method = "insert")
    long insertSelective(TbContent tbContent);

    @Delete("delete from tb_content where id = #{id}")
    int deleteContent(long id);
}