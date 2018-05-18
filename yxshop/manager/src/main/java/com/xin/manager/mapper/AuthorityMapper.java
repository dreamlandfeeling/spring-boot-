package com.xin.manager.mapper;

import com.xin.manager.model.Authority;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AuthorityMapper {
    int deleteByPrimaryKey(Long aid);

    int insert(Authority record);

    int insertSelective(Authority record);

    @Select("select * from authority a inner join role_authority ra on a.aid = ra.aid WHERE ra.rid = #{rid}")
    List<Authority> selectByRoleId(Long rid);

    Authority selectByPrimaryKey(Long aid);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}