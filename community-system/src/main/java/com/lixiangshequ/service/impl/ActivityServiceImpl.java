package com.lixiangshequ.service.impl;

import com.lixiangshequ.entity.Activity;
import com.lixiangshequ.entity.ActivityList;
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
        return activityMapper.selectByActId(id);
    }

    public Activity updateTime(Activity activity) {
        return null;
    }

    public String delete(int act_id) {
        int result = activityMapper.delect(act_id);
        if(result==0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    @Override
    public Activity insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public List selectNumByActId(int act_id) {
        return activityMapper.selectNumByActId(act_id);
    }

    @Override
    public boolean insertActivityNum(ActivityList activityList) {
        int r = activityMapper.insertActivityNum(activityList);
        if(r>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ActivityList selectIsApply(ActivityList activityList) {
        return activityMapper.selectByPrimaryKey(activityList);
    }
}
