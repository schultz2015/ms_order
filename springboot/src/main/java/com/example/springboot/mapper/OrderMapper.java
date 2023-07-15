package com.example.springboot.mapper;

import com.example.springboot.pojo.Houses;
import com.example.springboot.pojo.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM `order` WHERE orderid=#{orderid}")
    Orders findOrderById(int orderid);
    @Select("SELECT * FROM `order` WHERE roomname=#{roomname}")
    List<Orders> findByroomname(String roomname);
    @Select("SELECT * FROM `order` WHERE userid=#{userid}")
    List<Orders> findByUserid(int userid);
    @Select("SELECT * FROM `order` WHERE orderid=#{orderid}")
    List<Orders> findByOrderid(int orderid);
    @Select("SELECT * FROM `order` WHERE price=#{price}")
    List<Orders> findByPrice(int price);

    @Select("SELECT * FROM `order` WHERE userid=#{userid}")
    List<Orders> findMyOrder(int userid);
    @Select("SELECT * FROM `order`")
    List<Orders> findAllOrders();

    @Insert("INSERT INTO `order`(userid,houseid," +
            "roomid,price,tel," +
            "roomname,truename,start,end,cancel) " +
            "VALUES(#{userid},#{houseid}," +
            "#{roomid},#{price},#{tel}," +
            "#{roomname},#{truename},#{start},#{end},#{cancel})")
    int insertOrder( int userid,int houseid,
                    int roomid,int price,String tel,
                    String roomname, String truename,
                    String start,String end,String cancel);

    @Update("UPDATE `order` SET cancel=#{cancel} " +
            "where orderid=#{orderid}")
    int updateCancel(String cancel,int orderid);

    @Update("UPDATE `order` SET truename=#{truename} " +
            "where orderid=#{orderid}")
    int updateOrderName(String truename,int orderid);

    @Update("UPDATE `order` SET tel=#{tel} " +
            "where orderid=#{orderid}")
    int updateOrderTel(String tel,int orderid);

    @Update("UPDATE `order` SET roomname=#{roomname} " +
            "where orderid=#{orderid}")
    int updateOrderroomname(String roomname,int orderid);
    @Delete("DELETE FROM `order` where orderid=#{orderid}")
    int deleteOrder(int orderid);

    @Select("SELECT * FROM `order` WHERE roomid=#{roomid} AND houseid=#{houseid}")
    List<Orders> findOrderByRH(int roomid, int houseid);
}
