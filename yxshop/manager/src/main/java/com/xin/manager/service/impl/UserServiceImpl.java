package com.xin.manager.service.impl;

import com.xin.manager.mapper.TbUserMapper;
import com.xin.manager.mapper.UserMapper;
import com.xin.manager.model.TbUser;
import com.xin.manager.model.User;
import com.xin.manager.service.UserService;
import com.xin.manager.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {
        User user = userMapper.selectByUserName(username);
        return user;
    }
}
