package com.lixiangshequ.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7059272902617954794L;

    public User(){}

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

    private String certificate_no;

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
                ", certificate_no=" + certificate_no +
                '}';
    }
}
