package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "食堂实体类")
public class Canteen implements Serializable {

    @ApiModelProperty(name = "cno", value = "食堂Id", required = false)
    private Integer cno;
    @ApiModelProperty(name = "cname", value = "食堂名", required = true)
    private String cname;

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Canteen{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                '}';
    }
}
