package com.lixiangshequ.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorizationUser {

    @NotBlank
    private String tel;

    @NotBlank
    private String password;

    @Override
    public String toString() {
        return "{tel=" + tel  + ", password= ******}";
    }
}
