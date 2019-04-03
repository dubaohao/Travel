package com.example.travel.form;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class HodometerForm {
    private Integer hoId;
    private String hoName;
    private String picture;
//    private Integer gId;
    private BigDecimal price;
    private String category;
//    private Timestamp createDate;
//    private Timestamp updateDate;
//    private String status;
//    private String progress;
    private String hoUrl;
    private Integer hoNumUp;
    private Integer hoNumDown;
    private String strategyUrl;
}
