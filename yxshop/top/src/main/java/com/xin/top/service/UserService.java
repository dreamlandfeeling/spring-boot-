package com.xin.top.service;

import com.xin.top.dto.Result;
import com.xin.top.model.TbUser;

public interface UserService {
    Result checkUsername(String username);

    Result checkPhone(String phone);

    Result saveUser(TbUser tbUser);

    Result findUserByAccountAndPssword(String account, String password);
}
