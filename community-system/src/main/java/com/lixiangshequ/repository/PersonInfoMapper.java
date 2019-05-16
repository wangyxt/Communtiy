package com.lixiangshequ.repository;

import com.lixiangshequ.entity.PersonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PersonInfoMapper {

    int insertPerson(PersonInfo personInfo);

    List selectByName(String name);

    PersonInfo selectById(int id);

    PersonInfo selectByCard(String card);

    int update(PersonInfo personInfo);

    PersonInfo delete (PersonInfo personInfo);

    List selectAll();
}
