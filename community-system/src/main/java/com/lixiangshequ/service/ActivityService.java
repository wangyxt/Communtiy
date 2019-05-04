package com.lixiangshequ.service;

import com.lixiangshequ.entity.Activity;
import com.lixiangshequ.entity.ActivityList;

import java.util.List;

public interface ActivityService {

    /**
     * 查询所有活动
     * @return
     */
    List selectAll();

    /**
     * 根据发布人id查活动
     * @param id
     * @return
     */
    List selectByPublishId(int id);

    /**
     * 根据活动id查询
     * @param id
     * @return
     */
    Activity selectByActId(int id);

    /**
     * 修改活动开始、结束时间
     * @param activity
     * @return
     */
    Activity updateTime(Activity activity);

    /**
     * 删除活动
     * @param act_id
     */
    String delete(int act_id);

    /**
     * 添加活动
     * @param activity
     * @return
     */
    Activity insert(Activity activity);

    List selectNumByActId(int act_id);

    boolean insertActivityNum(ActivityList activityList);

    ActivityList selectIsApply(ActivityList activityList);
}
