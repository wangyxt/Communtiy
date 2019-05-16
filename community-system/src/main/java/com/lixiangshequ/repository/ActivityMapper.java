package com.lixiangshequ.repository;

import com.lixiangshequ.entity.Activity;
import com.lixiangshequ.entity.ActivityList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {
    /**
     * 查询所有审核通过活动
     * @return
     */
    List selectAll();

    /**
     * 根据发布人id查询活动
     * @return
     */
    List selectAllByPublishId(int publish_id);

    /**
     * 查询所有待审核活动
     * @return
     */
    List selectAllToAudit();

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
     * 修改活动内容
     * @param activity
     * @return
     */
    int update(Activity activity);

    /**
     * 删除活动
     * @param act_id
     */
    int delect(int act_id);

    int insert(Activity activity);

    List selectNumByActId(int act_id);

    int insertActivityNum(ActivityList activityList);

    ActivityList selectByPrimaryKey(ActivityList activityList);

    int deleteByPrimaryKey(ActivityList activityList);
}
