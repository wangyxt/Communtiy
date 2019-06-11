package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.Problem;
import com.lixiangshequ.repository.ProblemMapper;
import com.lixiangshequ.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemMapper mapper;

    @Autowired
    public ProblemServiceImpl(ProblemMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List getAllUntreatedProblem() {
        return mapper.getAllUntreatedProblem();
    }

    @Override
    public List getAllProcessedProblem() {
        return mapper.getAllProcessedProblem();
    }

    @Override
    public List getAllProblemByHandlerId(Integer id) {
        return mapper.getAllProblemByHandlerId(id);
    }

    @Override
    public List getAllProblemByUserId(Integer id) {
        return mapper.getAllProblemByUserId(id);
    }

    @Override
    public Problem selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Problem problem) {
        return mapper.insert(problem);
    }

    @Override
    public int update(Problem problem) {
        return mapper.updateByPrimaryKeySelective(problem);
    }

    @Override
    public int delete(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List selectAllProblem() {
        return mapper.selectAllProblem();
    }
}
