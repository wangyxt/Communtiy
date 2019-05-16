package com.lixiangshequ.service;

import com.lixiangshequ.entity.PersonInfo;

import java.util.List;

public interface PersonInfoService {

    int insertPerson(PersonInfo personInfo);

    List selectByName(String name);

    PersonInfo selectById(int id);

    PersonInfo selectByCard(String card);

    int update(PersonInfo personInfo);

    PersonInfo delete (PersonInfo personInfo);

    List selectAll();
}
