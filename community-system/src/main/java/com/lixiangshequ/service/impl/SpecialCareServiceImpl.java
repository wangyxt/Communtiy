package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.SpecialCare;
import com.lixiangshequ.repository.SpecialCareMapper;
import com.lixiangshequ.service.SpecialCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialCareServiceImpl implements SpecialCareService {

    private final SpecialCareMapper specialCareMapper;

    @Autowired
    public SpecialCareServiceImpl(SpecialCareMapper specialCareMapper) {
        this.specialCareMapper = specialCareMapper;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return specialCareMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SpecialCare record) {
        return specialCareMapper.insert(record);
    }

    @Override
    public int insertSelective(SpecialCare record) {
        return specialCareMapper.insertSelective(record);
    }

    @Override
    public SpecialCare selectByPrimaryKey(Integer id) {
        return specialCareMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SpecialCare record) {
        return specialCareMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SpecialCare record) {
        return specialCareMapper.updateByPrimaryKey(record);
    }

    @Override
    public List selectAll(int begin,int end,String...condition) {
        if (condition.length!=0){
            return specialCareMapper.selectAllByCondition(begin,end,condition[0]);
        }
        return specialCareMapper.selectAll(begin,end);
    }

    /**
     * 查询所有待审核对象
     * @return
     */
    @Override
    public List selectAllNotPass(){return specialCareMapper.selectAllNotPass();}
}
