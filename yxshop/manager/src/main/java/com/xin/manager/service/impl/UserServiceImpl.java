package com.xin.manager.service.impl;

import com.xin.manager.mapper.TbUserMapper;
import com.xin.manager.model.TbUser;
import com.xin.manager.service.UserService;
import com.xin.manager.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser selectByUsernameAndPassword(String username, String password) {
        password = MD5Utils.md5(password);
        TbUser tbUser = tbUserMapper.selectByUsernameAndPassword(username, password);
        return tbUser;
    }
}
