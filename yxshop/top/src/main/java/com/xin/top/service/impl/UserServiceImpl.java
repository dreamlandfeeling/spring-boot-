package com.xin.top.service.impl;

import com.xin.top.dto.Constant;
import com.xin.top.dto.Result;
import com.xin.top.mapper.TbUserMapper;
import com.xin.top.model.TbUser;
import com.xin.top.service.UserService;
import com.xin.top.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Result checkUsername(String username) {
        TbUser user = tbUserMapper.selectByUsername(username);
        if(user!=null){
            return Result.ok(false);
        }
        return Result.ok(true);
    }

    @Override
    public Result checkPhone(String phone) {
        TbUser user = tbUserMapper.selectByPhone(phone);
        if(user!=null){
            return Result.ok(false);
        }
        return Result.ok(true);
    }

    @Override
    public Result saveUser(TbUser tbUser) {
        if(!(boolean)checkUsername(tbUser.getUsername()).getData()){
            return Result.error("用户名已经注册");
        }
        if(!(boolean)checkPhone(tbUser.getPhone()).getData()){
            return Result.error("手机号已经注册");
        }
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUser.setPassword(MD5Utils.md5(tbUser.getPassword()));
        int result = tbUserMapper.insertSelective(tbUser);
        if(result<1){
            return Result.error("注册失败");
        }
        return Result.ok();
    }

    @Override
    public Result findUserByAccountAndPssword(String account, String password) {
        boolean result = false;
        password = MD5Utils.md5(password);
        TbUser user = tbUserMapper.selectByUsername(account);
        if(user!=null){
            user = tbUserMapper.selectByUsernameAndPassword(account, password);
            if(user!=null){
                return Result.ok(user);
            }
        }
        user = tbUserMapper.selectByEmail(account);
        if(user!=null){
            user = tbUserMapper.selectByEmailAndPassword(account, password);
            if(user!=null){
                return Result.ok(user);
            }
        }
        user = tbUserMapper.selectByPhone(account);
        if(user!=null){
            user = tbUserMapper.selectByPhoneAndPassword(account, password);
            if(user!=null){
                return Result.ok(user);
            }
        }
        if(result==false){
            return Result.error("手机/邮箱/用户名不正确");
        }
        return Result.error("密码不正确");
    }
}

