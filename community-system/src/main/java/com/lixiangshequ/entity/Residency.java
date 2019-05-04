package com.lixiangshequ.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 户籍信息
 */
@Data
public class Residency implements Serializable {

    private static final long serialVersionUID = 6107197030010606559L;

    private int uid;

    /**
     * 户主姓名
     */
    private String name;

    /**
     * 户籍结构
     */
    private String status;

    /**
     * 是否在本社区
     */
    private Boolean in_community;

    /**
     * 家庭联系电话
     */
    private String mobile;

    private int member;

    /**
     * 住房地址
     */
    private String building_add;

    /**
     * 房号名称
     */
    private String room_no;

    private Float room_area;

    private int floor;

    /**
     * 社区内住房数
     */
    private int room_count;

    /**
     * 备注
     */
    private String bak;
}
