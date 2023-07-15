package com.example.springboot.pojo;
import org.springframework.stereotype.Component;

@Component
public class Rooms {
    private int houseid;
    private int roomid;
    private int price;
    private String type;
    private String roomname;
    private String roomimg;

    public Rooms() {
    }

    public Rooms(int houseid, int roomid, int price, String type, String roomname, String roomimg) {
        this.houseid = houseid;
        this.roomid = roomid;
        this.price = price;
        this.type = type;
        this.roomname = roomname;
        this.roomimg = roomimg;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public String getRoomimg() {
        return roomimg;
    }

    public void setRoomimg(String roomimg) {
        this.roomimg = roomimg;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "houseid=" + houseid +
                ", roomid=" + roomid +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", roomname='" + roomname + '\'' +
                ", roomimg='" + roomimg + '\'' +
                '}';
    }
}
