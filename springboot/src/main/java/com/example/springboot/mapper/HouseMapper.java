package com.example.springboot.mapper;

import com.example.springboot.pojo.Houses;
import com.example.springboot.pojo.Rooms;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HouseMapper {
    @Select("SELECT * FROM house WHERE houseid=#{houseid}")
    Houses findHouseById(int houseid);

    @Select("SELECT * FROM house")
    List<Houses> findAllHouses();

    @Select("SELECT * FROM house WHERE houseid=#{houseid}")
    List<Houses> findByHouseid(int houseid);
    @Select("SELECT * FROM house WHERE housename=#{housename}")
    List<Houses> findByHousename(String housename);

    @Select("SELECT * FROM room ORDER BY houseid DESC  LIMIT 1  ")
    Houses findFirst();

    @Insert("INSERT INTO house(houseid,address,housename) " +
            "VALUES(#{houseid},#{address},#{housename})")
    int insertHouse(int houseid, String address, String housename);



    @Update("UPDATE house SET housename=#{housename} " +
            "where houseid=#{houseid}")
    int updateHouseName(String housename,int houseid);

    @Update("UPDATE house SET address=#{address} " +
            "where houseid=#{houseid}")
    int updateHouseAddress(String address,int houseid);
    ;

    @Delete("DELETE FROM house where houseid=#{houseid}")
    int deleteHouse(int houseid);

}