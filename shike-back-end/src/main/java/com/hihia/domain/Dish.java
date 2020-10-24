package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "菜品实体类")
public class Dish implements Serializable {

    @ApiModelProperty(name = "dno", value = "菜品Id", required = false)
    private Integer dno;
    @ApiModelProperty(name = "dname", value = "菜品名称", required = true)
    private String dname;
    @ApiModelProperty(name = "cno", value = "所属食堂", required = true)
    private Integer cno;
    @ApiModelProperty(name = "cname", value = "所属食堂名称", required = false)
    private String cname;
    @ApiModelProperty(name = "price", value = "价格", required = true)
    private Double price;

    public Integer getDno() {
        return dno;
    }

    public void setDno(Integer dno) {
        this.dno = dno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dno=" + dno +
                ", dname='" + dname + '\'' +
                ", cno=" + cno +
                ", price=" + price +
                '}';
    }
}
