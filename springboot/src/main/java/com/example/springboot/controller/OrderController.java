package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.pojo.Orders;
import com.example.springboot.pojo.User;
import com.example.springboot.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController extends BaseController {
    @Autowired
    IOrderService service;

    @GetMapping("/order")
    public List<Orders> order() {
        return service.getAllOrders();
    }

    @PostMapping("/MyOrder")
    public List<Orders> myorder(@RequestBody User user) {
        return service.getMyOrder(user.getUserid());
    }

    @PostMapping("/findOrderByRH")
    public List<Orders> findOrderByRH(@RequestBody Orders order){
        return service.findOrderByRH(order);
    }

    @PostMapping("/confirmOrder")
    public Result<?> confirmOrder(@RequestBody Orders order) {
        service.confirmOrder(order);
        return Result.success();
    }

    @PostMapping("/searchOrder")
    public List<Orders> searchOrder(@RequestBody Orders order) {
        return switch (order.getRoomid()) {
            case -4 -> service.searchOrder(-4, order);
            case -3 -> service.searchOrder(-3, order);
            case -2 -> service.searchOrder(-2, order);
            case -1 -> service.searchOrder(-1, order);
            default -> null;
        };
    }

    @PostMapping("/updateOrder")
    public Result<?> updateOrder(@RequestBody Orders order) {
        service.updateOrder(order);
        return Result.success();

    }
    @PostMapping("/updateOrderTel")
    public Result<?> updateOrderTel(@RequestBody Orders order) {
        service.updateOrderTel(order);
        return Result.success();
    }

    @PostMapping("/updateOrderTrueName")
    public Result<?> updateOrderTrueName(@RequestBody Orders order) {

        service.updateOrderTrueName(order);
        return Result.success();

    }
    @PostMapping("/deleteOrder")
    public Result<?> deleteOrder(@RequestBody Orders order) {
        service.deleteOrder(order);
        return Result.success();

    }

}
