package com.xin.manager.mapper;

import com.xin.manager.model.Role;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    int insertSelective(Role record);

    @Results({
            @Result(property = "rid",column = "rid"),
            @Result(property = "authorities",column = "rid",many=@Many(select = "com.xin.manager.mapper.AuthorityMapper.selectByRoleId",fetchType=FetchType.EAGER))
    })
    @Select("select * from role r inner join user_role ur on r.rid = ur.rid WHERE ur.uid = #{uid}")
    List<Role> selectByUserId(Long uid);

    Role selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}