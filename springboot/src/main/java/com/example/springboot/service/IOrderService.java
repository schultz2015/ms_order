package com.example.springboot.service;

import com.example.springboot.pojo.Orders;
import com.example.springboot.service.ex.*;

import java.util.List;

public interface IOrderService {
    List<Orders> getAllOrders() throws SelectException;
    List<Orders> getMyOrder(int userid) throws OrderNotfFoundException;
    void deleteOrder(Orders order) throws DeleteException;
    void updateOrder(Orders order) throws UpdateException;
    void updateOrderTel(Orders order) throws UpdateException;
    void updateOrderTrueName(Orders order) throws UpdateException;
    List<Orders> searchOrder(int i, Orders order) throws OrderNotfFoundException;
    List<Orders> findOrderByRH(Orders order) throws OrderNotfFoundException;
    void confirmOrder(Orders order) throws InsertException;
}
