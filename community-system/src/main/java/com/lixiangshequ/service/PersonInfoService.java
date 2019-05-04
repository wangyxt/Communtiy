package com.lixiangshequ.service;

import com.lixiangshequ.domain.PersonInfo;

import java.util.List;

public interface PersonInfoService {

    void insertPerson(PersonInfo personInfo);

    List selectByName(String name);

    PersonInfo selectById(int id);

    PersonInfo selectByCard(String card);

    PersonInfo update(PersonInfo personInfo);

    PersonInfo delete (PersonInfo personInfo);
}
