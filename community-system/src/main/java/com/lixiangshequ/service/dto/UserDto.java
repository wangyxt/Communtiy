package com.lixiangshequ.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -8308635982913079318L;

    private int uid;
    private String name;
    @JsonIgnore
    private String password;

    private int role;
    private String tel;

    private String email;

    private Timestamp creatTime;
}
