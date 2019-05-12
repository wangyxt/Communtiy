package com.lixiangshequ.repository;

import com.lixiangshequ.entity.Residency;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResidencyMapper {

    int insertResidency(Residency residency);

    int updateResidency(Residency residency);

    Residency selectResidencyById(int uid);

    Residency selectResidencyByName(String name);

    int deleteResidencyById(int uid);

    List selectAll();
}
