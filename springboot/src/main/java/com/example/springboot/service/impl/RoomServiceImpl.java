package com.example.springboot.service.impl;

import com.example.springboot.mapper.RoomMapper;
import com.example.springboot.pojo.Rooms;
import com.example.springboot.service.IRoomService;
import com.example.springboot.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements IRoomService {
    @Autowired
    RoomMapper roomMapper;

    public List<Rooms> getAllRooms() throws SelectException {
        List<Rooms> list = new ArrayList<>();
        list = roomMapper.findAllRooms();
//        if (list == null ) {
//            throw new SelectException("查询失败");
//        }
        //旅社的房间可以为空

        return list;
    }
    public void deleteRoom(Rooms room) throws DeleteException {
       int i= roomMapper.deleteRoom(room.getHouseid(),room.getRoomid());
       if(i!=1){
           throw new DeleteException("删除失败");
       }
    }
    public List<Rooms> searchRoom(Rooms room) throws RoomNotFoundException{
        List<Rooms> result = new ArrayList<>();
        Rooms ro = roomMapper.findRoomById(room.getHouseid(),room.getRoomid());
//        if (ro == null) {
//            throw new RoomNotFoundException("房间未找到");
//        }
        //旅社的房间可以为空
        result.add(ro);
        return result;
    }

    public void submitRName(Rooms room) throws UpdateException {
        int i=  roomMapper.updateRoomName(room.getRoomname(),room.getHouseid(),room.getRoomid());
        if (i!=1){
            throw new UpdateException("更新失败");
        }
    }

    public void submitPrice(Rooms room) throws UpdateException {
        int i=  roomMapper.updateRoomPrice(room.getPrice(),room.getHouseid(),room.getRoomid());
        if (i!=1){
            throw new UpdateException("更新失败");
        }
    }

    public void submitType(Rooms room) throws UpdateException{
        int i= roomMapper.updateRoomType(room.getType(),room.getHouseid(),room.getRoomid());
        if (i!=1){
            throw new UpdateException("更新失败");
        }
    }

    public void submitImg(Rooms room) throws UpdateException{
        int i= roomMapper.updateRoomImg(room.getRoomimg(),room.getHouseid(),room.getRoomid());
        if (i!=1){
            throw new UpdateException("更新失败");
        }
    }

    public Rooms findFirstRoom(Rooms room) throws RoomNotFoundException {
        Rooms r = roomMapper.findFirst(room.getHouseid());
        //旅社的房间可以为空
        return r;
    }

    public void InsertRoom(Rooms room) throws InsertException {
        int i= roomMapper.insertRoom(room.getHouseid(),room.getRoomid(),room.getPrice(),room.getType(),room.getRoomname(),room.getRoomimg());
        if (i!=1){
           throw new InsertException("插入失败");
        }
    }
}
