package com.example.springboot.service.impl;

import com.example.springboot.common.DateStringInt;
import com.example.springboot.mapper.HouseMapper;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.mapper.RoomMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.pojo.Orders;
import com.example.springboot.service.IMiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@org.springframework.stereotype.Service
public class MiscServiceImpl implements IMiscService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    HouseMapper houseMapper;




    public List<Integer> getChart(int requ,List<Orders> list) {
        class PD {
            int price;
            String date;
        }

        List<Orders> l = new ArrayList<>();
        for (Orders value : list
        ) {
            String[] thisyear = DateStringInt.StringData().split("-");
            String[] start = value.getStart().split("-");
            String[] end = value.getEnd().split("-");
            if (start[0].equals(thisyear[0])) {
                l.add(value);
            }
        }

        List<PD> pd = new ArrayList<>();
        for (Orders value : l) {
            String[] start = value.getStart().split("-");
            String[] end = value.getEnd().split("-");
            if (!start[1].equals(end[1])) {
                PD p = new PD();
                p.date = start[1] + "-" + (DateStringInt.datenum
                        (start[0], start[1])
                        - Integer.parseInt(start[2]));
                p.price = value.getPrice() * (DateStringInt.datenum
                        (start[0], start[1])
                        - Integer.parseInt(start[2]));

                PD d = new PD();
                d.date = end[1] + "-" + (end[2]);
                d.price = value.getPrice() * Integer.parseInt(end[2]);
                pd.add(p);
                pd.add(d);
            } else {
                PD p = new PD();
                p.date = start[1] + "-" + (DateStringInt.datenum
                        (start[0], start[1])
                        - Integer.parseInt(start[2]));
                p.price = value.getPrice() * (Integer.parseInt(end[2]) + (DateStringInt.datenum
                        (start[0], start[1])
                        - Integer.parseInt(start[2])));
                pd.add(p);
            }
        }


        List<PD> result = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for (PD value:pd
             ) {
            String[] start = value.date.split("-");
            if(date.isEmpty()){
                date.add(value.date);
                PD p = new PD();
                p.price=value.price;
                p.date=start[0];
                result.add(p);
            }
            if(date.contains(value.date)){
                for (PD d:result
                     ) {
                    if(d.date.equals(start[0])){
                        d.price+=value.price;
                    }
                }
            }else {
                date.add(value.date);
                PD p = new PD();
                p.price=value.price;
                p.date=start[0];
                result.add(p);
            }
        }


        Collections.sort(result, new Comparator<PD>() {
            @Override
            public int compare(PD o1, PD o2) {
                return Integer.parseInt(o1.date)-Integer.parseInt(o2.date);
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        List<Integer> D = new ArrayList<>();
        List<Integer> P = new ArrayList<>();
        for (PD d:result
             ) {
            D.add(Integer.parseInt(d.date));
            P.add(d.price);
        }
        if(requ==1){
            return P;
        }else return D;
    }







































//    public List<User> getAllUsers(){
//        List<User> list = new ArrayList<>();
//        list = userMapper.find();
//        for (int i = 0; i <list.size();i++) {
//            User u = new User();
//            u = list.get(i);
//        }
//        return list;
//    }

//    public List<Houses> getAllHouses(){
//        List<Houses> list = new ArrayList<>();
//        list = houseMapper.findAllHouses();
//        for (int i = 0; i <list.size();i++) {
//            Houses houses = new Houses();
//            houses = list.get(i);
//        }
//        return list;
//    }

//    public List<Rooms> getAllRooms() {
//        List<Rooms> list = new ArrayList<>();
//        list = roomMapper.findAllRooms();
//        for (Rooms value : list) {
//            Rooms rooms = new Rooms();
//            rooms = value;
//        }
//        return list;
//    }

//    public int CountAffirm(User user) {
//        User u= new User();
//        u=userMapper.findByName(user.getUsername());
//        System.out.println(u);
//        System.out.println(user);
//        if (user == null) {
//            return -1;
//        }
//        if (u==null){
//            return -2;
//        }
//        if(u.getUsername().equals(user.getUsername())){
//
//            if(u.getPassword().equals(user.getPassword())){
//
//                return 1;
//            }
//            else {
//                return -3;
//            }
//        }
//        else return -4;
//    }



//    public List<Orders> getAllOrders() {
//        List<Orders> list = new ArrayList<>();
//        list = orderMapper.findAllOrders();
//        for (Orders value : list) {
//            Orders order = new Orders();
//            order = value;
//        }
//        return list;
//    }

//    public User getMyInfo(User user) {
//        System.out.println(user);
//        return userMapper.findByName(user.getUsername());
//    }

//    public List<Orders> getMyOrder(int userid) {
//        List<Orders> orders = new ArrayList<>();
//        System.out.println(orderMapper.findMyOrder(userid));
//        orders=orderMapper.findMyOrder(userid);
//        return orders;
//    }


//    public int deleteUser(User user) {
//        System.out.println(user.getUserid());
//        return userMapper.deleteUser(user.getUserid());
//    }

//    public int deleteOrder(Orders order) {
//        System.out.println(order.getOrderid());
//        return orderMapper.deleteOrder(order.getOrderid());
//    }
//
//    public int updateOrder(Orders order) {
//        System.out.println(order.getOrderid());
//        return orderMapper.updateCancel(order.getCancel(),order.getOrderid());
//    }
//    public int updateOrderTel(Orders order) {
//        System.out.println(order.getOrderid());
//        return orderMapper.updateOrderTel(order.getTel(),order.getOrderid());
//    }
//    public int updateOrderTrueName(Orders order) {
//        System.out.println(order.getOrderid());
//        return orderMapper.updateOrderName(order.getTruename(),order.getOrderid());
//    }

//    public User searchUser(User user) {
//        System.out.println(user);
//        return userMapper.findById(user.getUserid());
//    }

//    public List<Orders> searchOrder(int i, Orders order) {
//        switch (i){
//            case -4:return orderMapper.findByPrice(order.getPrice());
//            case -3:return orderMapper.findByOrderid(order.getOrderid());
//            case -2:return orderMapper.findByUserid(order.getUserid());
//            case -1:return orderMapper.findByroomname(order.getRoomname());
//        }
//        return null;
//    }

//    public List<Houses> searchHouse(int i, Houses house) {
//        switch (i){
//            case -1: System.out.println("-1"+house.getHouseid());return houseMapper.findByHouseid(house.getHouseid());
//            case -2: System.out.println("-2"+house.getHousename());return houseMapper.findByHousename(house.getHousename());
//        }
//        return null;
//    }

//    public int deleteHouse(Houses house) {
//        System.out.println(house.getHouseid());
//        roomMapper.deleteRoomByHouseId(house.getHouseid());
//        return houseMapper.deleteHouse(house.getHouseid());
//    }

//    public int deleteRoom(Rooms room) {
//        System.out.println(room.getRoomid());
//        return roomMapper.deleteRoom(room.getHouseid(),room.getRoomid());
//    }

//    public int submitAddress(Houses house) {
//        System.out.println(house.getHouseid());
//        return houseMapper.updateHouseAddress(house.getAddress(),house.getHouseid());
//    }

//    public int submitName(Houses house) {
//        System.out.println(house.getHouseid());
//        return houseMapper.updateHouseName(house.getHousename(),house.getHouseid());
//    }

//    public List<Rooms> searchRoom(Rooms room) {
//        List<Rooms> result = new ArrayList<>();
//        Rooms ro = roomMapper.findRoomById(room.getHouseid(),room.getRoomid());
//        result.add(ro);
//        return result;
//    }

//    public int submitRName(Rooms room)     {return roomMapper.updateRoomName(room.getRoomname(),room.getHouseid(),room.getRoomid());
//    }
//
//    public int submitPrice(Rooms room)     {return roomMapper.updateRoomPrice(room.getPrice(),room.getHouseid(),room.getRoomid());
//    }
//
//    public int submitType(Rooms room)       {return roomMapper.updateRoomType(room.getType(),room.getHouseid(),room.getRoomid());
//    }
//
//    public int submitImg(Rooms room)       {return roomMapper.updateRoomImg(room.getRoomimg(),room.getHouseid(),room.getRoomid());
//    }
//
//    public Rooms findFirstRoom(Rooms room) {return roomMapper.findFirst(room.getHouseid());
//    }
//
//    public int InsertRoom(Rooms room)      {return roomMapper.insertRoom(room.getHouseid(),room.getRoomid(),room.getPrice(),room.getType(),room.getRoomname(),room.getRoomimg());
//    }

//    public int InsertHouse(Houses house) {
//        return houseMapper.insertHouse(house.getHouseid(), house.getAddress(),house.getHousename());
//    }


//    public Houses findFirstHouse(Houses house) {
//        return houseMapper.findFirst();
//    }

//    public int submitusername(User user) {
//        return userMapper.updateUserName(user.getUsername(),user.getUserid());
//    }
//
//    public int submitpassword(User user) {
//        return userMapper.updateUserPassword(user.getPassword(),user.getUserid());
//    }
//
//    public int submittruename(User user) {
//        return userMapper.updateTrueName(user.getTruename(),user.getUserid());
//    }
//
//    public int submittel(User user) {
//        return userMapper.updateUserTel(user.getTel(),user.getUserid());
//    }

//    public Houses findHouseById(int houseid) {
//        return houseMapper.findHouseById(houseid);
//    }

//    public List<Orders> findOrderByRH(Orders order) {
//        List<Orders> orders = new ArrayList<>();
//        orders=orderMapper.findOrderByRH(order.getRoomid(),order.getHouseid());
//        return orders;
//    }
//
//
//    public int confirmOrder(Orders order) {
//        return orderMapper.insertOrder(order.getUserid(),order.getHouseid(),order.getRoomid(),
//                order.getPrice(),order.getTel(),order.getRoomname(),order.getTruename(),order.getStart(),order.getEnd(),order.getCancel());
//    }

//    public int Register(User user) {
//        return  userMapper.insert(user.getUsername(),user.getPassword(), user.getTruename(),user.getTel());
//    }
}


