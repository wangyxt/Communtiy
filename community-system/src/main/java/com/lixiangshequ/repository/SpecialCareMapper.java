package com.lixiangshequ.repository;

import com.lixiangshequ.entity.SpecialCare;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SpecialCareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpecialCare record);

    int insertSelective(SpecialCare record);

    SpecialCare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpecialCare record);

    int updateByPrimaryKey(SpecialCare record);

    List selectAll();
}