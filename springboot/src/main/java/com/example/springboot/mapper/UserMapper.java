package com.example.springboot.mapper;

import com.example.springboot.pojo.Orders;
import com.example.springboot.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username=#{username}")
    User findByName(String username);

    @Select("SELECT * FROM user WHERE userid=#{userid}")
    User findById(int userid);


    @Select("SELECT * FROM user")
    List<User> find();



    @Insert("INSERT INTO user(username,password,truename,tel,salt) " +
            "VALUES(#{username},#{password},#{truename},#{tel},#{salt})")
    int insert(String username,String password,String truename,String tel,String salt);

//    @Update("UPDATE user SET username=#{username} where userid=#{userid}")
//    int updateUserName(String username,int userid);
//
//    @Update("UPDATE user SET password=#{password} where userid=#{userid}")
//    int updateUserPassword(String password,int userid);
//
//    @Update("UPDATE user SET truename=#{truename} where userid=#{userid}")
//    int updateTrueName(String truename,int userid);
//
//    @Update("UPDATE user SET tel=#{tel} where userid=#{userid}")
//    int updateUserTel(String tel,int userid);

    @Delete("DELETE FROM user where userid=#{userid}")
    int deleteUser(int userid);

    int modify(User user);

    int submitpassword(User user);
}
