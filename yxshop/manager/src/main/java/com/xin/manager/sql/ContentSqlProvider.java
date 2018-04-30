package com.xin.manager.sql;

import com.xin.manager.model.TbContent;
import com.xin.manager.model.TbContentCategory;
import com.xin.manager.utils.StringUtils;
import com.xin.manager.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;


public class ContentSqlProvider {

    public String insert(final TbContent cat) {
        String sql = new SQL() {
            {
                INSERT_INTO("tb_content");
                VALUES("id","#{id}");
                if (StringUtils.isNotBlank(cat.getCategoryId())) {
                    VALUES("category_id","#{categoryId}");
                }
                if (StringUtils.isNotBlank(cat.getTitle())) {
                    VALUES("title" ,"#{title}");
                }
                if (StringUtils.isNotBlank(cat.getSubTitle())) {
                    VALUES("sub_title","#{subTitle}");
                }
                if (StringUtils.isNotBlank(cat.getContent())) {
                    VALUES("content" ,"#{content}");
                }
                if (StringUtils.isNotBlank(cat.getTitleDesc())) {
                    VALUES("title_desc","#{titleDesc}");
                }
                if (StringUtils.isNotBlank(cat.getUrl())) {
                    VALUES("url","#{url}");
                }
                if (StringUtils.isNotBlank(cat.getPic())) {
                    VALUES("pic","#{pic}");
                }
                if (StringUtils.isNotBlank(cat.getPic2())) {
                    VALUES("pic2","#{pic2}");
                }
                VALUES("created","#{created}");
                VALUES("updated","#{updated}");
            }
        }.toString();
        return sql;
    }

    public String update(final TbContent cat) {
        String sql = new SQL() {
            {
                UPDATE("tb_content");

                if (StringUtils.isNotBlank(cat.getCategoryId())) {
                    SET("category_id = #{categoryId}");
                }
                if (StringUtils.isNotBlank(cat.getTitle())) {
                    SET("title = #{title}");
                }
                if (StringUtils.isNotBlank(cat.getSubTitle())) {
                    SET("sub_title = #{subTitle}");
                }
                if (StringUtils.isNotBlank(cat.getContent())) {
                    SET("content = #{content}");
                }
                if (StringUtils.isNotBlank(cat.getTitleDesc())) {
                    SET("title_desc = #{titleDesc}");
                }
                if (StringUtils.isNotBlank(cat.getUrl())) {
                    SET("url = #{url}");
                }
                if (StringUtils.isNotBlank(cat.getPic())) {
                    SET("pic = #{pic}");
                }
                if (StringUtils.isNotBlank(cat.getPic2())) {
                    SET("pic2 = #{pic2}");
                }
                SET("updated = #{updated}");
                WHERE("id = #{id}");
            }
        }.toString();
        return sql;
    }
}
