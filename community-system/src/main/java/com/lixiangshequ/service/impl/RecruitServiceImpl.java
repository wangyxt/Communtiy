package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.Recruit;
import com.lixiangshequ.repository.RecruitMapper;
import com.lixiangshequ.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    private RecruitMapper mapper;

    @Override
    public List getAllRecruit() {
        return mapper.selectAll();
    }

    @Override
    public int updateRecruit(Recruit recruit) {
        return mapper.updateByPrimaryKey(recruit);
    }

    @Override
    public int deleteRecruit(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Recruit selectByPrimaryKey(Integer id){
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Recruit recruit) {
        return mapper.insert(recruit);
    }

    @Override
    public int update(Recruit recruit) {
        return mapper.updateByPrimaryKey(recruit);
    }
}
