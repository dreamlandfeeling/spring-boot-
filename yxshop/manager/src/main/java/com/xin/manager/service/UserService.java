package com.xin.manager.service;

import com.xin.manager.model.TbUser;
import com.xin.manager.model.User;

public interface UserService {

    User findUserByUserName(String username);
}
