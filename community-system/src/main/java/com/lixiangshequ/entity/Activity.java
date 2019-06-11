package com.lixiangshequ.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Getter
@Setter
public class Activity implements Serializable {
    private static final long serialVersionUID = 7334028841050274385L;

    private int act_id;
    private User publish_id;
    private String type;
    private Timestamp start_time;
    private Timestamp end_time;
    private String detail;
    private int state;

    @Override
    public String toString() {
        return "Activity{" +
                "act_id=" + act_id +
                ", publish_id=" + publish_id +
                ", type='" + type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", detail='" + detail + '\'' +
                ", state=" + state +
                '}';
    }
}
