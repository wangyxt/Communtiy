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

    @Override
    public List selectAll() {
        return activityMapper.selectAll();
    }

    @Override
    public List selectAllByPublishId(int id) {
        return activityMapper.selectAllByPublishId(id);
    }

    @Override
    public List selectAllToAudit() {
        return activityMapper.selectAllToAudit();
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
    public int insert(Activity activity) {
        return activityMapper.insert(activity);
    }

    @Override
    public List selectNumByActId(int act_id) {
        return activityMapper.selectNumByActId(act_id);
    }

    /**
     * 报名，返回ture代表报名成功
     * @param activityList
     * @return
     */
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

    @Override
    public void cancelActivityNum(ActivityList activityList) {
        activityMapper.deleteByPrimaryKey(activityList);
    }

    @Override
    public int update(Activity activity) {
        return activityMapper.update(activity);
    }
}
