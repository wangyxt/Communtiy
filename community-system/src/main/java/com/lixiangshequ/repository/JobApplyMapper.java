package com.lixiangshequ.repository;

import com.lixiangshequ.entity.JobApply;

public interface JobApplyMapper {
    int insert(JobApply record);

    int insertSelective(JobApply record);
}