package com.lixiangshequ.repository;

import com.lixiangshequ.entity.Park;

public interface ParkMapper {
    int deleteByPrimaryKey(Integer parkingId);

    int insert(Park record);

    int insertSelective(Park record);

    Park selectByPrimaryKey(Integer parkingId);

    int updateByPrimaryKeySelective(Park record);

    int updateByPrimaryKey(Park record);
}