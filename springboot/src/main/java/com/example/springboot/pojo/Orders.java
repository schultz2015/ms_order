package com.example.springboot.pojo;

public class Orders {
    private int orderid;
    private int userid;
    private int houseid;
    private int roomid;
    private int price;
    private String tel;
    private String roomname;
    private String truename;
    private String start;
    private String end;

    private String cancel;

    public int getOrderid() {
        return orderid;
    }

    public int getUserid() {
        return userid;
    }

    public int getHouseid() {
        return houseid;
    }

    public int getRoomid() {
        return roomid;
    }

    public int getPrice() {
        return price;
    }

    public String getTel() {
        return tel;
    }

    public String getRoomname() {
        return roomname;
    }

    public String getTruename() {
        return truename;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getCancel() {
        return cancel;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderid=" + orderid +
                ", userid=" + userid +
                ", houseid=" + houseid +
                ", roomid=" + roomid +
                ", price=" + price +
                ", tel='" + tel + '\'' +
                ", roomname='" + roomname + '\'' +
                ", truename='" + truename + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", cancel='" + cancel + '\'' +
                '}';
    }

    public Orders(int orderid, int userid, int houseid, int roomid, int price, String tel, String roomname, String truename, String start, String end, String cancel) {
        this.orderid = orderid;
        this.userid = userid;
        this.houseid = houseid;
        this.roomid = roomid;
        this.price = price;
        this.tel = tel;
        this.roomname = roomname;
        this.truename = truename;
        this.start = start;
        this.end = end;
        this.cancel = cancel;
    }

    public Orders() {
    }
}
