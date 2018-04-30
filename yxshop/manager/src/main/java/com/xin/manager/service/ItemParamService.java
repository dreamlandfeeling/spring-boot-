package com.xin.manager.service;

import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.model.TbItemParam;
import com.xin.manager.sql.ItemSqlProvider;

import java.util.List;

public interface ItemParamService {


    Result insert(TbItemParam tbItemParam);

    Result update(TbItemParam tbItemParam);

    Result updateToChanged(TbItemParam tbItemParam);

    Result delete(long id);

    PageBean findItemByPage(int page, int rows);
}
