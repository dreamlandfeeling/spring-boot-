package com.xin.manager.service;

import com.xin.manager.dto.PageBean;
import com.xin.manager.dto.Result;
import com.xin.manager.model.TbContent;
import com.xin.manager.model.TbContentCategory;

import java.util.List;

public interface ContentService {
    Result findByPrimaryKey(long id);
    Result insertContent(TbContent tbContent);
    Result insertContentCat(TbContentCategory tbContentCategory);
    Result updateContentToChanged(TbContent tbContent);
    Result updateContentCatToChanged(TbContentCategory tbContentCategory);
    Result deleteContent(long id);
    Result deleteContentCat(long id);
    PageBean findContentByPage(int page, int rows, long categoryId);
}
