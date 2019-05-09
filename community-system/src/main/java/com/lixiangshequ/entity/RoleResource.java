package com.lixiangshequ.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class RoleResource implements Serializable {
    private static final long serialVersionUID = -4922498807941776714L;

    private int id;

    private int roleId;

    private String resource;

    private String url;
}
