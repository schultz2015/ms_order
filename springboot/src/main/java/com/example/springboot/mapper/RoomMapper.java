package com.example.springboot.mapper;

import com.example.springboot.pojo.Houses;
import com.example.springboot.pojo.Rooms;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper {
    @Select("SELECT * FROM room WHERE houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    Rooms findRoomById(int houseid,int roomid);
    @Select("SELECT * FROM room WHERE houseid=#{houseid} ORDER BY roomid DESC  LIMIT 1  ")
    Rooms findFirst(int houseid);
    @Select("SELECT * FROM room")
    List<Rooms> findAllRooms();

    @Insert("INSERT INTO room(houseid,roomid,price," +
            "type,roomname,roomimg) " +
            "VALUES(#{houseid},#{roomid},#{price}," +
            "#{type},#{roomname},#{roomimg})")
    int insertRoom(int houseid,int roomid,int price,
                    String type, String roomname,
                    String roomimg);



    @Update("UPDATE room SET roomname=#{roomname} " +
            "where houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    int updateRoomName(String roomname,int houseid,int roomid);

    @Update("UPDATE room SET type=#{type} " +
            "where houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    int updateRoomType(String type,int houseid,int roomid);

    @Update("UPDATE room SET roomimg=#{roomimg} " +
            "where houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    int updateRoomImg(String roomimg,int houseid,int roomid);

    @Update("UPDATE room SET price=#{price} " +
            "where houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    int updateRoomPrice(int price,int houseid,int roomid);

    @Delete("DELETE FROM room where houseid=#{houseid} " +
            "AND roomid=#{roomid}")
    int deleteRoom(int houseid,int roomid);
    @Delete("DELETE FROM room where houseid=#{houseid} ")
    int deleteRoomByHouseId(int houseid);

}
