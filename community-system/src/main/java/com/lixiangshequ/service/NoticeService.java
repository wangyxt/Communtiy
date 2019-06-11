package com.lixiangshequ.service;

import com.lixiangshequ.entity.Notice;

import java.util.List;

public interface NoticeService {

    List selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);

    List selectAllWaitNotice();
}
