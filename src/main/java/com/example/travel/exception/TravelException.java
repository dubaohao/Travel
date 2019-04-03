package com.example.travel.exception;

import com.example.travel.enums.ResultEnum;

//自己定义的抛出错误定义类
public class TravelException extends RuntimeException {
    private Integer code;
    public TravelException(Integer code,String message){
        super(message);
        this.code=code;
    }

    public TravelException(ResultEnum orderNotExist) {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}