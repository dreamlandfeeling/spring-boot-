package com.xin.manager.service;

import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.model.TbItem;

import java.util.List;
import java.util.Map;

public interface ItemService {

    Result findItemDescByPrimaryKey(Long id);

    Result listItemCat();

    Result insert(TbItem tbItem,String desc);

    Result insertItemDesc(Long id,String desc);

    Result update(TbItem tbItem);

    Result updateToChanged(TbItem tbItem);

    Result delete(long id);

    PageBean findItemByPage(Map<String,Object> params);
    //PageBean findItemByPage(int page, int rows);

    Result batchDelete(Long[] ids);
}
