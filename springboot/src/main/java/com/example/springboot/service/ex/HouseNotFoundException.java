package com.example.springboot.service.ex;

public class HouseNotFoundException extends ServiceException{
    public HouseNotFoundException() {
    }

    public HouseNotFoundException(String message) {
        super(message);
    }

    public HouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public HouseNotFoundException(Throwable cause) {
        super(cause);
    }

    public HouseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
