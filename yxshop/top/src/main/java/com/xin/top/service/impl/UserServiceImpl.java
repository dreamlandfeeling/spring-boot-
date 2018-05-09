package com.xin.top.service.impl;

import com.xin.top.dto.Result;
import com.xin.top.mapper.TbUserMapper;
import com.xin.top.mapper.TbUserShippingMapper;
import com.xin.top.model.TbUser;
import com.xin.top.model.TbUserShipping;
import com.xin.top.service.UserService;
import com.xin.top.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserShippingMapper tbUserShippingMapper;

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
    public Result findUserShippingAddress(Long id) {
        TbUserShipping tbUserShipping = tbUserShippingMapper.selectByPrimaryKey(id);
        System.err.println(tbUserShipping.getAddress());
        return Result.ok(tbUserShipping);
    }

    @Override
    public Result addUserShippingAddress(TbUserShipping tbUserShipping) {
        tbUserShippingMapper.insert(tbUserShipping);
        return Result.ok(tbUserShipping.getId());
    }

    @Override
    public Result updateUserShippingAddress(TbUserShipping userShipping) {
        tbUserShippingMapper.updateByPrimaryKey(userShipping);
        return Result.ok(userShipping.getId());
    }

    @Override
    public Result deleteUserShippingAddress(Long id) {
        tbUserShippingMapper.deleteByPrimaryKey(id);
        return Result.ok();
    }

    @Override
    public Result findUserByUsernameAndPssword(String account, String password) {
        password = MD5Utils.md5(password);
        TbUser user = tbUserMapper.selectByUsername(account);
        if(user!=null){
            user = tbUserMapper.selectByUsernameAndPassword(account, password);
            if(user!=null){
                return Result.ok(user);
            }
        }else{
            return Result.error("用户名不正确");
        }
        return Result.error("密码不正确");
    }

    @Override
    public Result findUserByPhoneAndPssword(String phone, String password) {
        password = MD5Utils.md5(password);
        TbUser user = tbUserMapper.selectByPhone(phone);
        if(user!=null){
            user = tbUserMapper.selectByPhoneAndPassword(phone, password);
            if(user!=null){
                return Result.ok(user);
            }
        }else{
            return Result.error("手机号不正确");
        }
        return Result.error("密码不正确");
    }

    @Override
    public Result findUserByEmailAndPssword(String email, String password) {
        password = MD5Utils.md5(password);
        TbUser user = tbUserMapper.selectByEmail(email);
        if(user!=null){
            user = tbUserMapper.selectByEmailAndPassword(email, password);
            if(user!=null){
                return Result.ok(user);
            }
        }else{
            return Result.error("邮箱不正确");
        }
        return Result.error("密码不正确");
    }
}

