package com.lixiangshequ.repository;

import com.lixiangshequ.entity.ProblemType;

public interface ProblemTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(ProblemType record);

    int insertSelective(ProblemType record);

    ProblemType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(ProblemType record);

    int updateByPrimaryKey(ProblemType record);
}