package com.example.springboot.service.impl;

import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.pojo.Orders;
import com.example.springboot.service.IOrderService;
import com.example.springboot.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    public List<Orders> getAllOrders() throws SelectException {
        List<Orders> list = new ArrayList<>();
        list = orderMapper.findAllOrders();
//        if (list == null ) {
//            throw new SelectException("查询失败");
//        }
        //可以为空
        return list;
    }

    public List<Orders> getMyOrder(int userid) throws OrderNotfFoundException {
        List<Orders> orders = orderMapper.findMyOrder(userid);
//        if (orders == null) {
//            throw new OrderNotfFoundException("订单查询失败");
//        }
        //可以为空
        return orders;
    }
    public void deleteOrder(Orders order) throws DeleteException {
        int i = orderMapper.deleteOrder(order.getOrderid());
        if (i != 1) {
            throw new DeleteException("删除失败");
        }
    }

    public void updateOrder(Orders order) throws UpdateException {
        int i = orderMapper.updateCancel(order.getCancel(),order.getOrderid());
        if (i != 1) {
            throw new UpdateException("更新失败");
        }
    }
    public void updateOrderTel(Orders order) throws UpdateException{
        int i =  orderMapper.updateOrderTel(order.getTel(),order.getOrderid());
        if (i != 1) {
            throw new UpdateException("更新失败");
        }
    }
    public void updateOrderTrueName(Orders order) throws UpdateException{
        int i = orderMapper.updateOrderName(order.getTruename(),order.getOrderid());
        if (i != 1) {
            throw new UpdateException("更新失败");
        }
    }
    public List<Orders> searchOrder(int i, Orders order) throws OrderNotfFoundException{
        List<Orders> orders = switch (i) {
            case -4 -> orderMapper.findByPrice(order.getPrice());
            case -3 -> orderMapper.findByOrderid(order.getOrderid());
            case -2 -> orderMapper.findByUserid(order.getUserid());
            case -1 -> orderMapper.findByroomname(order.getRoomname());
            default -> new ArrayList<>();
        };
//        if (orders==null) {
//            throw new OrderNotfFoundException("查询失败");
//        }
        //可以为空
        return orders;
    }
    public List<Orders> findOrderByRH(Orders order) throws OrderNotfFoundException {
        List<Orders> orders =orderMapper.findOrderByRH(order.getRoomid(),order.getHouseid());
        //查询过往订单，可以为空
        return orders;
    }


    public void confirmOrder(Orders order) throws InsertException {
        int i= orderMapper.insertOrder(order.getUserid(),order.getHouseid(),order.getRoomid(),
                order.getPrice(),order.getTel(),order.getRoomname(),order.getTruename(),order.getStart(),order.getEnd(),order.getCancel());
        if(i!=1){
            throw new InsertException("订单插入失败");
        }
    }
}
