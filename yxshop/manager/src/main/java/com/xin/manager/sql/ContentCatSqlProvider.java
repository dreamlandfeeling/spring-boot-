package com.xin.manager.sql;

import com.xin.manager.model.TbContentCategory;
import com.xin.manager.utils.StringUtils;
import com.xin.manager.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;


public class ContentCatSqlProvider {

    public String update(final TbContentCategory cat) {
        String sql = new SQL() {
            {
                UPDATE("tb_content_category");
                if (StringUtils.isNotBlank(cat.getParentId())) {
                    SET("parent_id = #{parentId}");
                }
                if (StringUtils.isNotBlank(cat.getStatus())) {
                    SET("status = #{status}");
                }
                if (StringUtils.isNotBlank(cat.getSortOrder())) {
                    SET("sort_order = #{sortOrder}");
                }
                if (StringUtils.isNotBlank(cat.getName())) {
                    SET("name = #{name}");
                }
                if (cat.getIsParent() != null) {
                    SET("is_parent = #{isParent}");
                }
                if (cat.getUpdated() != null) {
                    SET("updated = #{updated}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
        return sql;
    }
}
