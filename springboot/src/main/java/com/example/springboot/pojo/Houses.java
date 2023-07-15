package com.example.springboot.pojo;

import org.springframework.stereotype.Component;

@Component
public class Houses {
    private int houseid;
    private String address;
    private String housename;
    private String houseimg;

    public Houses() {
    }

    public Houses(int houseid, String address, String housename, String houseimg) {
        this.houseid = houseid;
        this.address = address;
        this.housename = housename;
        this.houseimg = houseimg;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getHouseimg() {
        return houseimg;
    }

    public void setHouseimg(String houseimg) {
        this.houseimg = houseimg;
    }

    @Override
    public String toString() {
        return "Houses{" +
                "houseid=" + houseid +
                ", address='" + address + '\'' +
                ", housename='" + housename + '\'' +
                ", houseimg='" + houseimg + '\'' +
                '}';
    }
}
