package com.lixiangshequ.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
public class Activity implements Serializable {
    private static final long serialVersionUID = 7334028841050274385L;

    private int act_id;
    private User publish_id;
    private String type;
    private Date start_time;
    private Date end_time;
    private String desc;
    private int state;

    @Override
    public String toString() {
        return "Activity{" +
                "act_id=" + act_id +
                ", publish_id=" + publish_id +
                ", type='" + type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", desc='" + desc + '\'' +
                ", state=" + state +
                '}';
    }
}
