package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.PersonInfo;
import com.lixiangshequ.repository.PersonInfoMapper;
import com.lixiangshequ.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoMapper personInfoMapper;

    @Override
    public int insertPerson(PersonInfo personInfo) {
       return personInfoMapper.insertPerson(personInfo);
    }

    @Override
    public List selectByName(String name) {
        return personInfoMapper.selectByName(name);
    }

    @Override
    public PersonInfo selectById(int id) {
        return personInfoMapper.selectById(id);
    }

    @Override
    public PersonInfo selectByCard(String card) {
        return personInfoMapper.selectByCard(card);
    }

    @Override
    public int update(PersonInfo personInfo) {
        return personInfoMapper.update(personInfo);
    }

    @Override
    public PersonInfo delete(PersonInfo personInfo) {
        return personInfoMapper.delete(personInfo);
    }

    @Override
    public List selectAll() {
        return personInfoMapper.selectAll();
    }
}
