package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.Notice;
import com.lixiangshequ.repository.NoticeMapper;
import com.lixiangshequ.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper mapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Notice record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Notice record) {
        return mapper.insertSelective(record);
    }

    @Override
    public Notice selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Notice record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Notice record) {
        return mapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Notice record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public List selectAllWaitNotice() {
        return mapper.selectAllWaitNotice();
    }
}
