package com.lixiangshequ.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = 5438146329214336495L;

    private int person_id;

    private String name;

    private int age;

    private String sex;

    private String certificate;

    private String certificate_no;

    private Residency residency_id;

    private String relation;

    private Boolean marriage;

    private Date birthday;

    private String ethnic;

    private String health;

    private String soldier_status;

    private String career;

    private String religion;

    private String residency_type;

    private String address;

    private String connection_phone;

    private String email;

    private Boolean volunteer;

    private String culture;

    private String photo_path;

}
