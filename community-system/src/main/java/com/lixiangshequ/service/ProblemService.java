package com.lixiangshequ.service;

import com.lixiangshequ.entity.Problem;

import java.util.List;

public interface ProblemService {

    List getAllUntreatedProblem();

    List getAllProcessedProblem();

    List getAllProblemByHandlerId(Integer id);

    List getAllProblemByUserId(Integer id);

    Problem selectById(Integer id);

    int insert(Problem problem);

    int update(Problem problem);

    int delete(Integer id);

    List selectAllProblem();
}
