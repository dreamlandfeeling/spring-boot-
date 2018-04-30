package com.xin.manager.sql;


import com.xin.manager.model.TbItem;
import com.xin.manager.utils.StringUtils;
import com.xin.manager.utils.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;


public class ItemSqlProvider {

    public String update(final TbItem tbItem) {
        return new SQL() {
            {
                UPDATE("tb_item");
                if(StringUtils.isNotBlank(tbItem.getTitle())){
                    SET("title = #{title}");
                }
                if(StringUtils.isNotBlank(tbItem.getSellPoint())){
                    SET("sell_point = #{sellPoint}");
                }
                if(StringUtils.isNotBlank(tbItem.getPrice())){
                    SET("price = #{price}");
                }
                if(StringUtils.isNotBlank(tbItem.getNum())){
                    SET("num = #{num}");
                }
                if(StringUtils.isNotBlank(tbItem.getBarcode())){
                    SET("barcode = #{barcode}");
                }
                if(StringUtils.isNotBlank(tbItem.getImage())){
                    SET("image = #{image}");
                }
                if(StringUtils.isNotBlank(tbItem.getCid())){
                    SET("cid = #{cid}");
                }
                if(StringUtils.isNotBlank(tbItem.getStatus())){
                    SET("status = #{status}");
                }
                if(tbItem.getUpdated()!=null){
                    SET("updated = #{updated}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }

    public String queryByParams(final Map params) {
        return new SQL() {
            {
                SELECT("* from tb_item");
                WHERE("status = 1");
                Object obj = params.get("search");
                if(obj!=null){
                    String str = obj.toString();
                    if(StringUtils.isNotBlank(str)){
                        WHERE("title like '%"+str+"%'");
                        WHERE("sell_point like '%"+str+"%'");
                    }
                }
            }
        }.toString();
    }


    public String countItemByParams(final Map params) {
        return new SQL() {
            {
                SELECT("count(*) from tb_item");
                WHERE("status = 1");
                Object obj = params.get("search");
                if(obj!=null){
                    String str = obj.toString();
                    if(StringUtils.isNotBlank(str)){
                        WHERE("title like '%"+str+"%'");
                        WHERE("sell_point like '%"+str+"%'");
                    }
                }
            }
        }.toString();
    }
}
