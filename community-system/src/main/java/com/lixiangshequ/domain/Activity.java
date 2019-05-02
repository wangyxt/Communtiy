package com.lixiangshequ.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Activity implements Serializable {
    private static final long serialVersionUID = 7334028841050274385L;

    private int act_id;
    private User publish_id;
    private String type;
    private Date start_time;
    private Date end_time;
    private String desc;
    private int state;
}
