package com.xin.top.mapper;

import com.xin.top.model.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    TbUser selectByUsername(String username);

    TbUser selectByUsernameAndPassword(@Param("username") String username, @Param("password")String password);

    TbUser selectByEmail(String email);

    TbUser selectByEmailAndPassword(@Param("email")String email,@Param("password")String password);

    TbUser selectByPhone(String phone);

    TbUser selectByPhoneAndPassword(@Param("phone")String phone,@Param("password")String password);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);
}