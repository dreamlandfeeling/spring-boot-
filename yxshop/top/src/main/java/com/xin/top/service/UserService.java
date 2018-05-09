package com.xin.top.service;

import com.xin.top.dto.Result;
import com.xin.top.model.TbUser;
import com.xin.top.model.TbUserShipping;

public interface UserService {
    Result checkUsername(String username);

    Result checkPhone(String phone);

    Result saveUser(TbUser tbUser);

    Result addUserShippingAddress(TbUserShipping tbUserShipping);

    Result findUserByUsernameAndPssword(String username, String password);
    Result findUserByPhoneAndPssword(String phone, String password);
    Result findUserByEmailAndPssword(String email, String password);

    Result deleteUserShippingAddress(Long id);

    Result findUserShippingAddress(Long id);

    Result updateUserShippingAddress(TbUserShipping userShipping);
}
