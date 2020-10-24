package com.hihia.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 邓梁
 * @date 2020/7/7 10:26
 * @email 18221221@bjtu.edu.cn
 */

@ApiModel(value = "用户账号实体类")
public class Account implements Serializable {

    @ApiModelProperty(name = "id", value = "账号Id", required = false)
    private Integer id;
    @ApiModelProperty(name = "username", value = "账号用户名", required = true)
    private String username;
    @ApiModelProperty(name = "password", value = "账号密码", required = true)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
