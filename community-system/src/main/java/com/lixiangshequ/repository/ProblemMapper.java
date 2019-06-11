package com.lixiangshequ.repository;

import com.lixiangshequ.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProblemMapper {
    int deleteByPrimaryKey(Integer problemId);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);

    List getAllUntreatedProblem();

    List getAllProcessedProblem();

    List getAllProblemByHandlerId(Integer id);

    List getAllProblemByUserId(Integer id);

    List selectAllProblem();
}