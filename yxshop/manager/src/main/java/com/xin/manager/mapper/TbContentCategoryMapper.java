package com.xin.manager.mapper;

import com.xin.manager.model.TbContentCategory;
import com.xin.manager.sql.ContentCatSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TbContentCategoryMapper {
    @Insert("insert into tb_content_category values(#{id},#{parentId},#{name},#{status},#{sortOrder},#{isParent}," +
            "#{created},#{updated})")
    int insertContentCat(TbContentCategory tbContentCategory);


    @Select("select * from tb_content_category where id = #{id} and status =1")
    TbContentCategory findByPrimaryKey(long id);

    @Select("select * from tb_content_category where parent_id = #{id} and status =1")
    List<TbContentCategory> findByParentId(long id);

    @Select("select MAX(id) from tb_content_category")
    long findMaxContentCatId();

    @Update("update tb_content_category set is_parent = #{isParent} where id = #{id}")
    int updateContentCatParentId(@Param("id") long id, @Param("isParent")boolean isParent);
    @UpdateProvider(type = ContentCatSqlProvider.class,method = "update")
    int updateToChanged(TbContentCategory tbContentCategory);

    @Update("update tb_content_category set status = 2 where id = #{id}")
    int deleteContentCat(long id);


}