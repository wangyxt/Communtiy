package com.lixiangshequ.repository;

import com.lixiangshequ.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    User selectById(@Param("id") int id);

    User selectByTel(String tel);

    User selectByName(String name);

    int insertOne(User user);

    String getRole(String name);

    List selectResourceByRoleId(int roleId);

    List selectAll(@Param("begin") int begin,@Param("end") int end);

    void deleteUser(@Param("id") int id);

    int update(User resouces);
}
