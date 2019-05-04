package com.lixiangshequ.repository;

import com.lixiangshequ.domain.PersonInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PersonInfoMapper {

    void insertPerson(PersonInfo personInfo);

    List selectByName(String name);

    PersonInfo selectById(int id);

    PersonInfo selectByCard(String card);

    PersonInfo update(PersonInfo personInfo);

    PersonInfo delete (PersonInfo personInfo);
}
