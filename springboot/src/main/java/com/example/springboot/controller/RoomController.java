package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.pojo.Rooms;
import com.example.springboot.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController extends BaseController{
    @Autowired
    IRoomService service;

    @GetMapping("/room")
    public List<Rooms> room(){
        return  service.getAllRooms();
    }


    @PostMapping("/searchRoom")
    public List<Rooms> searchRoom(@RequestBody Rooms room) {
        return service.searchRoom(room);
    }

    @PostMapping("/deleteRoom")
    public Result<?> deleteRoom(@RequestBody Rooms room ) {
        service.deleteRoom(room);
        return Result.success();

    }

    @PostMapping("/InsertRoom")
    public Result<?> InsertRoom(@RequestBody Rooms room) {
        service.InsertRoom(room);
        return Result.success();
    }

    @PostMapping("/submitRName")
    public Result<?> submitRName(@RequestBody Rooms room) {
        service.submitRName(room);
        return Result.success();
    }

    @PostMapping("/findFirstRoom")
    public Rooms findFirstRoom(@RequestBody Rooms room) {
        System.out.println(room);
        return service.findFirstRoom(room);
    }

    @PostMapping("/submitPrice")
    public Result<?> submitPrice(@RequestBody Rooms room) {

        service.submitPrice(room);
        return Result.success();

    }

    @PostMapping("/submitType")
    public Result<?> submitType(@RequestBody Rooms room) {

        service.submitType(room);

        return Result.success();

    }
    @PostMapping("/submitImg")
    public Result<?> submitImg(@RequestBody Rooms room) {
        service.submitImg(room);
        return Result.success();

    }
}
