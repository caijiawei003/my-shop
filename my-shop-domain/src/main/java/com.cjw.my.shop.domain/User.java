package com.cjw.my.shop.domain;

import java.io.Serializable;

/**
 * @program:my-shop
 * @description:
 * @author:xxs_cjw
 * @create:2019-12-30 16:33
 **/
public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private Boolean isRemember;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Boolean getRemember() {
        return isRemember;
    }

    public void setRemember(Boolean remember) {
        isRemember = remember;
    }
}
