package com.example.travel.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name="hodometer")
@Data
@DynamicUpdate
public class Hodometer {
    @Id
    @GeneratedValue
    private Integer hoId;
    private String hoName;
    private String picture;
    private Integer gId;
    private BigDecimal price;
    private String category;
    private Timestamp createDate;
    private Timestamp updateDate;
    private String status;
    private String progress;
    private String hoUrl;
    private Integer hoNumUp;
    private Integer hoNumDown;
    private String strategyUrl;

    public Hodometer() {
    }

    public Hodometer(String hoName, String picture, Integer gId, BigDecimal price, String category, String status, String progress, String hoUrl, Integer hoNumUp, Integer hoNumDown, String strategyUrl) {
        this.hoName = hoName;
        this.picture = picture;
        this.gId = gId;
        this.price = price;
        this.category = category;
        this.status = status;
        this.progress = progress;
        this.hoUrl = hoUrl;
        this.hoNumUp = hoNumUp;
        this.hoNumDown = hoNumDown;
        this.strategyUrl = strategyUrl;
    }
}
