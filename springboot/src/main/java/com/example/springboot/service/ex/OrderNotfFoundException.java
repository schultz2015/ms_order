package com.example.springboot.service.ex;

public class OrderNotfFoundException extends ServiceException{
    public OrderNotfFoundException() {
    }

    public OrderNotfFoundException(String message) {
        super(message);
    }

    public OrderNotfFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotfFoundException(Throwable cause) {
        super(cause);
    }

    public OrderNotfFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
