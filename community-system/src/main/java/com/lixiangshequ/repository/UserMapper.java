package com.lixiangshequ.repository;

import com.lixiangshequ.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User selectById(int id);

    User selectByTel(String tel);

    User selectByName(String name);

    int insertOne(User user);
}
