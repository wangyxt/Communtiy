package com.lixiangshequ.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityList {

    private int act_id;

    private int uid;

    public ActivityList(){}

    public ActivityList(int act_id, int uid) {
        this.act_id = act_id;
        this.uid = uid;
    }
}
