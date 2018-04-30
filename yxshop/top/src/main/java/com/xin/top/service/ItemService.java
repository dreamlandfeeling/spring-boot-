package com.xin.top.service;

import com.xin.top.dto.Result;
import com.xin.top.model.TbItem;

import java.util.Map;

public interface ItemService {

    Result findItemByPrimaryKey(Long id);
    Result findItemDescByPrimaryKey(Long id);
    Result findItemByPrimaryKeyToCart(Long id,int num);

}
