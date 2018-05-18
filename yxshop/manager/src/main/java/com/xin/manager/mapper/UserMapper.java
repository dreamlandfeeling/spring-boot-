package com.xin.manager.mapper;

import com.xin.manager.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    @Results({
            @Result(property = "uid",column = "uid"),
            @Result(property = "roles",column = "uid",many=@Many(select = "com.xin.manager.mapper.RoleMapper.selectByUserId",fetchType=FetchType.EAGER))
    })
    @Select("select * from user where name = #{username}")
    User selectByUserName(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}