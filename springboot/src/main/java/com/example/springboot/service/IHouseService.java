package com.example.springboot.service;

import com.example.springboot.pojo.Houses;
import com.example.springboot.service.ex.*;

import java.util.List;

public interface IHouseService {
    List<Houses> getAllHouses() throws SelectException;
    List<Houses> searchHouse(int i, Houses house) throws HouseNotFoundException;
    void deleteHouse(Houses house) throws DeleteException;
    void submitAddress(Houses house) throws UpdateException;
    void submitName(Houses house) throws UpdateException;
    void InsertHouse(Houses house) throws InsertException;
    Houses findFirstHouse(Houses house) throws HouseNotFoundException;
    Houses findHouseById(int houseid) throws HouseNotFoundException;
}
