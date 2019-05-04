package com.lixiangshequ.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 7059272902617954794L;

    private int uid;
    @NotNull
    private String name;
    @NotNull
    private String password;

    private int role;
    @NotNull
    private String tel;

    private String email;

    private Timestamp creatTime;

    private PersonInfo person_id;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", creatTime=" + creatTime +
                ", person_id=" + person_id +
                '}';
    }
}
