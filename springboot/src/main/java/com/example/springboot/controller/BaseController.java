package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handleException(Exception e){
        Result<Void> result = new Result<>();
        result.setMsg(e.getMessage());
        if (e instanceof DeleteException) {
            result.setCode("4001");
        } else if (e instanceof HouseNotFoundException) {
            result.setCode("4002");
        } else if (e instanceof InsertException) {
            result.setCode("4003");
        } else if (e instanceof OrderNotfFoundException) {
            result.setCode("4004");
        } else if (e instanceof PassWordNotMatchException) {
            result.setCode("4005");
        } else if (e instanceof RoomNotFoundException) {
            result.setCode("4006");
        } else if (e instanceof UpdateException) {
            result.setCode("4007");
        } else if (e instanceof UserNotFoundException) {
            result.setCode("4008");
        } else if (e instanceof FileException){
            result.setCode("4009");
        } else if (e instanceof UsernameDuplicateException){
            result.setCode("4010");
        }
        return result;
    }
}
