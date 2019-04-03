package com.example.travel.VO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GHodometerVO {
    private Integer hoId;
    private String hoName;
    private String picture;
    private Integer gId;
    private Integer price;
    private String gCategory;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String status;
    private String progress;
    private String hoUrl;
    private Integer hoNumUp;
    private Integer hoNumDown;
    private String strategyUrl;
}
