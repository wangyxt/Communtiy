package com.lixiangshequ.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ReturnStatus implements Serializable {

    private static final long serialVersionUID = 8810161822057091864L;
    public int code;

    public String msg;

    public int count;

    public Object data;

    public ReturnStatus(int code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
}
