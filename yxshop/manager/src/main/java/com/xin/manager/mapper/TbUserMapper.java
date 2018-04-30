package com.xin.manager.mapper;

import com.xin.manager.model.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface TbUserMapper {
    @Select("SELECT * FROM tb_user WHERE username = #{username} AND password = #{password}")
    TbUser selectByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}