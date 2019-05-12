package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.Residency;
import com.lixiangshequ.repository.ResidencyMapper;
import com.lixiangshequ.service.ResidencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidencyServiceImpl implements ResidencyService {

    private final ResidencyMapper residencyMapper;

    @Autowired
    public ResidencyServiceImpl(ResidencyMapper residencyMapper) {
        this.residencyMapper = residencyMapper;
    }

    @Override
    public int insertResidency(Residency residency) {
        return residencyMapper.insertResidency(residency);
    }

    @Override
    public int updateResidency(Residency residency) {
        return residencyMapper.updateResidency(residency);
    }

    @Override
    public Residency selectResidencyById(int uid) {
        return residencyMapper.selectResidencyById(uid);
    }

    @Override
    public Residency selectResidencyByName(String name) {
        return residencyMapper.selectResidencyByName(name);
    }

    @Override
    public int deleteResidencyById(int uid) {
        return residencyMapper.deleteResidencyById(uid);
    }

    @Override
    public List selectAll() {
        return residencyMapper.selectAll();
    }
}
