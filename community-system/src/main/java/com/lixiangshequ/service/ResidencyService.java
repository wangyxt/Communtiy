package com.lixiangshequ.service;

import com.lixiangshequ.entity.Residency;

import java.util.List;

public interface ResidencyService {

    int insertResidency(Residency residency);

    int updateResidency(Residency residency);

    Residency selectResidencyById(int uid);

    Residency selectResidencyByName(String name);

    int deleteResidencyById(int uid);

    List selectAll();
}
