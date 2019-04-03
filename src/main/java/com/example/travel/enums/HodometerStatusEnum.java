package com.example.travel.enums;


import lombok.Getter;

@Getter
public enum HodometerStatusEnum  implements CodeEnum{
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    HodometerStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
