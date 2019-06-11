package com.lixiangshequ.service;

import com.lixiangshequ.entity.Recruit;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecruitService {

    List getAllRecruit();

    int updateRecruit(Recruit recruit);

    int deleteRecruit(Integer id);

    Recruit selectByPrimaryKey(Integer id);

    int insert(Recruit recruit);

    int update(Recruit recruit);
}
