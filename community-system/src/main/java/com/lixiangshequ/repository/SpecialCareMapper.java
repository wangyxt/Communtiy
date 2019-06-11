package com.lixiangshequ.repository;

import com.lixiangshequ.entity.SpecialCare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List selectAll(@Param("begin") int begin, @Param("end") int end);

    List selectAllNotPass();

    List selectAllByCondition(@Param("begin") int begin, @Param("end") int end, @Param("s") String s);
}