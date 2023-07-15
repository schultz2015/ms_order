package com.example.springboot.controller;

import ch.qos.logback.core.util.FileUtil;
import com.example.springboot.common.FILE;
import com.example.springboot.common.Result;
import com.example.springboot.pojo.Houses;
import com.example.springboot.pojo.Orders;
import com.example.springboot.pojo.Rooms;
import com.example.springboot.service.IHouseService;
import com.example.springboot.service.IMiscService;
import com.example.springboot.service.IOrderService;
import com.example.springboot.service.IRoomService;
import com.example.springboot.service.impl.HouseServiceImpl;
import com.example.springboot.service.impl.OrderServiceImpl;
import com.example.springboot.service.impl.RoomServiceImpl;
import com.example.springboot.service.impl.MiscServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MiscController extends BaseController {
//    @Autowired
//    UserMapper userMap;
//
//    @Autowired
//    Goods goods;
    @Autowired
    IMiscService service;
    @Autowired
    IHouseService houseservice;
    @Autowired
    IRoomService roomservice;
    @Autowired
    IOrderService orderService;



    @PostMapping("/upload")
    public String addFile(MultipartFile file) throws IOException {
        String url = FILE.uploadFile(file);
        return url;
    }

    @GetMapping("/chartDate")
    public List<Integer> chartDate() {
        List<Orders> list = orderService.getAllOrders();
        return service.getChart(2,list);
    }
    @GetMapping("/chartPrice")
    public List<Integer> chartPrice() {
        List<Orders> list = orderService.getAllOrders();
        return service.getChart(1,list);
    }

    @PostMapping("/searchIndex")
    public List<Rooms> searchIndex(@RequestBody Object str) {
        String[] t = str.toString().split("stringindex");
        String[] r = t[1].split("=");
        String[] c = r[1].split("}");
        String s=c[0].trim();
        System.err.println(s);


        List<Houses> hList = houseservice.getAllHouses();
        List<Integer> housesID = new ArrayList<>();
        for (Houses houses: hList) {
            if(check(s)){
                if (houses.getAddress().contains(s) ||
                        houses.getHousename().contains(s)) {
                    housesID.add(houses.getHouseid());
                }
            }
        }

        List<Rooms> rList = roomservice.getAllRooms();
        List<Rooms> itemList = new ArrayList<>();

        String type,roomname;
        for(int position=0;position<rList.size();position++){
            Rooms ro = rList.get(position);
            type= ro.getType().trim();
            roomname = ro.getRoomname().trim();
            System.out.println(type);
            System.out.println(roomname);
            if(check(s)){
                if (type.toLowerCase().contains(s.toLowerCase())||
                        roomname.toLowerCase().contains(s.toLowerCase())) {
                    itemList.add(ro);
                }else if(housesID.contains(ro.getHouseid())){
                    itemList.add(ro);
                }
            }

        }
        System.err.println(itemList);
        return itemList;


    }
    public boolean check(String s) {
        if (!s.equals("")) {
            if (s.trim().length() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


















    //    @GetMapping("/user")
//    public List<User> user() {
//        return service.getAllUsers();
//    }
//    @GetMapping("/order")
//    public List<Orders> order() {
//        return service.getAllOrders();
//    }
//    @GetMapping("/house")
//    public List<Houses> house(){
//
//        return  service.getAllHouses();
//    }
//    @GetMapping("/room")
//    public List<Rooms> room(){
//        return  service.getAllRooms();
//    }

//    @PostMapping("/findHouseById")
//    public Houses findHouseById(@RequestBody Rooms rooms){
//        System.out.println(rooms);
//        return service.findHouseById(rooms.getHouseid());
//    }

//    @PostMapping("/findOrderByRH")
//    public List<Orders> findOrderByRH(@RequestBody Orders order){
//        System.out.println(order);
//        return service.findOrderByRH(order);
//    }
//
//    @PostMapping("/confirmOrder")
//    public Result<?> confirmOrder(@RequestBody Orders order) {
//        int code = service.confirmOrder(order);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/MyInfo")
//    public User myInfo(@RequestBody User user) {
//        User u = new User();
//        u=service.getMyInfo(user);
//        System.out.println(u);
//        return u;
//    }

//    @PostMapping("/searchUser")
//    public User searchUser(@RequestBody User user) {
//        User u = new User();
//        System.out.println("!!!!"+user);
//        u=service.searchUser(user);
//        System.out.println(u);
//        return u;
//    }
//    @PostMapping("/searchOrder")
//    public List<Orders> searchOrder(@RequestBody Orders order) {
//        System.out.println(order);
//        switch (order.getRoomid()){
//            case -4:return service.searchOrder(-4,order);
//            case -3:return service.searchOrder(-3,order);
//            case -2:return service.searchOrder(-2,order);
//            case -1:return service.searchOrder(-1,order);
//        }
//        return null;
//    }

//    @PostMapping("/searchHouse")
//    public List<Houses> searchHouse(@RequestBody Houses house) {
//        System.out.println(house);
//        switch (house.getAddress()){
//            case "-2": System.out.println(service.searchHouse(-2,house)); return service.searchHouse(-2,house);
//            case "-1": System.out.println(service.searchHouse(-1,house)); return service.searchHouse(-1,house);
//        }
//        return null;
//    }

//    @PostMapping("/searchRoom")
//    public List<Rooms> searchRoom(@RequestBody Rooms room) {
//        System.out.println(room);
//        return service.searchRoom(room);
//    }

//    @PostMapping("/MyOrder")
//    public List<Orders> myorder(@RequestBody User user) {
//        System.out.println(user.getUserid());
//        return service.getMyOrder(user.getUserid());
//    }

//    @PostMapping("/submittruename")
//    public Result<?> submittruename(@RequestBody User user) {
//        int code = service.submittruename(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//
//    @PostMapping("/submittel")
//    public Result<?> submittel(@RequestBody User user) {
//        int code = service.submittel(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//    @PostMapping("/submitpassword")
//    public Result<?> submitpassword(@RequestBody User user) {
//        int code = service.submitpassword(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//    @PostMapping("/submitusername")
//    public Result<?> submitusername(@RequestBody User user) {
//        int code = service.submitusername(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//
//    @PostMapping("/deleteUser")
//    public Result<?> deleteUser(@RequestBody User user) {
//        int code = service.deleteUser(user);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/deleteOrder")
//    public Result<?> deleteOrder(@RequestBody Orders order) {
//        int code = service.deleteOrder(order);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/deleteHouse")
//    public Result<?> deleteHouse(@RequestBody Houses house) {
//        int code = service.deleteHouse(house);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//    @PostMapping("/deleteRoom")
//    public Result<?> deleteRoom(@RequestBody Rooms room ) {
//        int code = service.deleteRoom(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/InsertRoom")
//    public Result<?> InsertRoom(@RequestBody Rooms room) {
//        System.out.println(room);
//        int code = service.InsertRoom(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/InsertHouse")
//    public Result<?> InsertHouse(@RequestBody Houses house ) {
//        System.out.println(house);
//        int code = service.InsertHouse(house);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/updateOrder")
//    public Result<?> updateOrder(@RequestBody Orders order) {
//        System.out.println(order);
//        int code = service.updateOrder(order);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//    @PostMapping("/updateOrderTel")
//    public Result<?> updateOrderTel(@RequestBody Orders order) {
//        System.out.println(order);
//        int code = service.updateOrderTel(order);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//
//    @PostMapping("/updateOrderTrueName")
//    public Result<?> updateOrderTrueName(@RequestBody Orders order) {
//        System.out.println(order);
//        int code = service.updateOrderTrueName(order);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/submitName")
//    public Result<?> submitName(@RequestBody Houses house) {
//        System.out.println(house);
//        int code = service.submitName(house);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//    @PostMapping("/submitAddress")
//    public Result<?> submitAddress(@RequestBody Houses house) {
//        System.out.println(house);
//        int code = service.submitAddress(house);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/submitRName")
//    public Result<?> submitRName(@RequestBody Rooms room) {
//        System.out.println(room);
//        int code = service.submitRName(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }
//
//    @PostMapping("/findFirstRoom")
//    public Rooms findFirstRoom(@RequestBody Rooms room) {
//        System.out.println(room);
//        return service.findFirstRoom(room);
//    }

//    @PostMapping("/findFirstHouse")
//    public Houses findFirstHouse(@RequestBody Houses house) {
//        System.out.println(house);
//        return service.findFirstHouse(house);
//    }

//    @PostMapping("/submitPrice")
//    public Result<?> submitPrice(@RequestBody Rooms room) {
//        System.out.println(room);
//        int code = service.submitPrice(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/submitType")
//    public Result<?> submitType(@RequestBody Rooms room) {
//        System.out.println(room);
//        int code = service.submitType(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/submitImg")
//    public Result<?> submitImg(@RequestBody Rooms room) {
//        System.out.println(room);
//        int code = service.submitImg(room);
//        if (code == 1) {
//            return ResultUtil.success();
//        }else return ResultUtil.error(1,"不存在");
//    }

//    @PostMapping("/LoginIn")
//    public Result<?> LoginIn(@RequestBody User user) {
//        int code = service.CountAffirm(user);
//        if(code==1){
//            System.out.println(user);
//            return ResultUtil.success();
//        }
//        else if(code==-2)
//        {
//            System.out.println("WrongCountInput");
//            return ResultUtil.error(1,"不存在");
//        }
//        else if(code == -3){
//            return ResultUtil.error(2,"密码错误");
//        }else return ResultUtil.error(3,"输入错误");
//    }
//
//
//    @PostMapping("/register")
//    public Result<?> register(@RequestBody User user) {
//        if(service.CountAffirm(user)==1 || service.CountAffirm(user)==-3){
//            return ResultUtil.error(6,"用户名存在");
//        }
//        else {
//            int code = service.Register(user);
//            System.out.println(code);
//            if(code==1){
//                System.out.println(user);
//                return ResultUtil.success();
//            }
//        }
//        return null;
//    }
}
