package com.example.springboot.service;

import com.example.springboot.pojo.User;
import com.example.springboot.service.ex.*;

import java.util.List;

public interface IUserService {
     List<User> getAllUsers() throws SelectException;

     void modify(User user) throws UpdateException;

    User CountAffirm(User user) throws UserNotFoundException, PassWordNotMatchException;
    User getMyInfo(User user) throws UserNotFoundException;
    void deleteUser(User user) throws UserNotFoundException,DeleteException  ;

    User searchUser(User user) throws UserNotFoundException;

//    int submitusername(User user);
//    int submitpassword(User user);
//    int submittruename(User user);
//    int submittel(User user);
    void Register(User user)throws UsernameDuplicateException, InsertException;

    void submitpassword(User user ) throws UpdateException;
}

