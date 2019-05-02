package com.lixiangshequ.service.impl;

import com.lixiangshequ.domain.Activity;
import com.lixiangshequ.domain.User;
import com.lixiangshequ.repository.ActivityMapper;
import com.lixiangshequ.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    public List selectAll() {
        return activityMapper.selectAll();
    }

    public List selectByPublishId(int id) {
        return null;
    }

    public Activity selectByActId(int id) {
        return null;
    }

    public Activity updateTime(Activity activity) {
        return null;
    }

    public void delete(User user, Activity activity) {

    }
}
