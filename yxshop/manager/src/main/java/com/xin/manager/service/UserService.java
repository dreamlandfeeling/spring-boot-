package com.xin.manager.service;

import com.xin.manager.model.TbUser;

public interface UserService {
    TbUser selectByUsernameAndPassword(String username,  String password);
}
