package com.lixiangshequ.service;

import com.lixiangshequ.entity.SpecialCare;

import java.util.List;

public interface SpecialCareService {

    int deleteByPrimaryKey(Integer id);

    int insert(SpecialCare record);

    int insertSelective(SpecialCare record);

    SpecialCare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialCare record);

    int updateByPrimaryKey(SpecialCare record);

    List selectAll(int begin,int end);

    List selectAllNotPass();
}
