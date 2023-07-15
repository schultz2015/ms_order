package com.example.springboot.service;

import com.example.springboot.pojo.Orders;

import java.util.List;

public interface IMiscService {
    List<Integer> getChart(int requ, List<Orders> list);
}
