package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.pojo.Houses;
import com.example.springboot.pojo.Rooms;
import com.example.springboot.service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HouseController extends BaseController {

    @Autowired
    IHouseService service;



    @GetMapping("/house")
    public List<Houses> house(){

        return  service.getAllHouses();
    }
    @PostMapping("/findHouseById")
    public Houses findHouseById(@RequestBody Rooms rooms){
        return service.findHouseById(rooms.getHouseid());
    }
    @PostMapping("/searchHouse")
    public List<Houses> searchHouse(@RequestBody Houses house) {
        return switch (house.getAddress()) {
            case "-2" -> service.searchHouse(-2, house);
            case "-1" -> service.searchHouse(-1, house);
            default -> null;
        };
    }
    @PostMapping("/deleteHouse")
    public Result<?> deleteHouse(@RequestBody Houses house) {
        service.deleteHouse(house);
        return Result.success();
    }

    @PostMapping("/InsertHouse")
    public Result<?> InsertHouse(@RequestBody Houses house ) {
        service.InsertHouse(house);
        return Result.success();
    }

    @PostMapping("/submitName")
    public Result<?> submitName(@RequestBody Houses house) {
        service.submitName(house);
        return Result.success();
    }
    @PostMapping("/submitAddress")
    public Result<?> submitAddress(@RequestBody Houses house) {
        service.submitAddress(house);
        return Result.success();

    }

    @PostMapping("/findFirstHouse")
    public Houses findFirstHouse(@RequestBody Houses house) {
        return service.findFirstHouse(house);
    }

}
