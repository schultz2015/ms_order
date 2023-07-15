package com.example.springboot.service;

import com.example.springboot.pojo.Orders;
import com.example.springboot.pojo.Rooms;
import com.example.springboot.service.ex.*;

import java.util.List;

public interface IRoomService {
    List<Rooms> getAllRooms() throws SelectException;
    void deleteRoom(Rooms room) throws DeleteException;
    List<Rooms> searchRoom(Rooms room) throws RoomNotFoundException;

    void submitRName(Rooms room) throws UpdateException;
    void submitPrice(Rooms room) throws UpdateException;
    void submitType(Rooms room) throws UpdateException;
    void submitImg(Rooms room) throws UpdateException;
    Rooms findFirstRoom(Rooms room) throws RoomNotFoundException;
    void InsertRoom(Rooms room) throws InsertException;

}
