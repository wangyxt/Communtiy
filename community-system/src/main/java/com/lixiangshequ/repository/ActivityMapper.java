package com.lixiangshequ.repository;

import com.lixiangshequ.domain.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityMapper {
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
     * 修改活动内容
     * @param activity
     * @return
     */
    Activity update(Activity activity);

    /**
     * 删除活动
     * @param activity
     */
    void delect(Activity activity);
}
