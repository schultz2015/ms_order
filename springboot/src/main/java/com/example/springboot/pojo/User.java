package com.example.springboot.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {
    private int userid;
    private String username; //用户名

    private String password;
    private String truename;
    private String tel;

    private String salt;
    public User() {
    }

    public User(int userid, String username, String password, String truename, String tel, String salt) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.truename = truename;
        this.tel = tel;
        this.salt = salt;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", truename='" + truename + '\'' +
                ", tel='" + tel + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}