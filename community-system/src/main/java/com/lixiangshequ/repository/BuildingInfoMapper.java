package com.lixiangshequ.repository;

import com.lixiangshequ.entity.BuildingInfo;

public interface BuildingInfoMapper {
    int deleteByPrimaryKey(Integer buildingId);

    int insert(BuildingInfo record);

    int insertSelective(BuildingInfo record);

    BuildingInfo selectByPrimaryKey(Integer buildingId);

    int updateByPrimaryKeySelective(BuildingInfo record);

    int updateByPrimaryKey(BuildingInfo record);
}