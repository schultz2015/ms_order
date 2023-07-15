package com.example.springboot.service.impl;

import com.example.springboot.mapper.HouseMapper;
import com.example.springboot.mapper.RoomMapper;
import com.example.springboot.pojo.Houses;
import com.example.springboot.service.IHouseService;
import com.example.springboot.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseServiceImpl implements IHouseService {
    @Autowired
    HouseMapper houseMapper;
    @Autowired
    RoomMapper roomMapper;

    public List<Houses> getAllHouses() throws SelectException {
        List<Houses> list = houseMapper.findAllHouses();
//        if (list == null ){
//            throw new SelectException("查询失败");
//        }
        //可以为空

        return list;
    }

    public List<Houses> searchHouse(int i, Houses house) throws HouseNotFoundException {
        List<Houses> result = switch (i) {
            case -1 -> houseMapper.findByHouseid(house.getHouseid());
            case -2 -> houseMapper.findByHousename(house.getHousename());
            default -> new ArrayList<>();
        };
        //可以为空
//        if (result == null) {
//            throw new HouseNotFoundException("民宿查询失败");
//        }
        return result;
    }

    public void deleteHouse(Houses house) throws DeleteException {
        int i = roomMapper.deleteRoomByHouseId(house.getHouseid());
        if (i != 1) {
            throw new DeleteException("删除失败");
        }
    }

    public void submitAddress(Houses house) throws UpdateException {
        int i =houseMapper.updateHouseAddress(house.getAddress(),house.getHouseid());
        if (i !=1) {
            throw new UpdateException("更新失败");
        }
    }

    public void submitName(Houses house) throws UpdateException {
        int i =houseMapper.updateHouseName(house.getHousename(),house.getHouseid());
        if (i !=1) {
            throw new UpdateException("更新失败");
        }
    }
    public void InsertHouse(Houses house) throws InsertException {
        int i = houseMapper.insertHouse(house.getHouseid(), house.getAddress(),house.getHousename());
        if (i !=1) {
            throw new InsertException("插入失败");
        }
    }
    public Houses findFirstHouse(Houses house) throws HouseNotFoundException{
        Houses result= houseMapper.findFirst();
//        if (result == null) {
//            throw new HouseNotFoundException("民宿查询失败");
//        }
        //可以为空
        return result;
    }

    public Houses findHouseById(int houseid) throws HouseNotFoundException {
        Houses result= houseMapper.findHouseById(houseid);
//        if (result == null) {
//            throw new HouseNotFoundException("民宿查询失败");
//        }
        //可以为空
        return result;
    }
}
