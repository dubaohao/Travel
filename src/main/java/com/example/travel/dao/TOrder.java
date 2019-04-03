package com.example.travel.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name="t_order")
@Data
@DynamicUpdate
public class TOrder {
    @Id
    @GeneratedValue
    private Integer orderId;
    private String orderNumber;
    private String groupNumber;
    private Integer hoId;
    private Integer gId;
    private Integer vId;
    private BigDecimal orderNum;
    private BigDecimal money;
    private String name;
    private String tel;
    private String wx;
    private String orderStatus;
    private String orderProgress;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Date goDate;

    public TOrder() {
    }

    public TOrder(String orderNumber, String groupNumber, Integer hoId, Integer gId, Integer vId, BigDecimal orderNum, BigDecimal money, String name, String tel, String wx, String orderStatus, String orderProgress, Timestamp createDate, Timestamp updateDate, Timestamp goDate) {
        this.orderNumber = orderNumber;
        this.groupNumber = groupNumber;
        this.hoId = hoId;
        this.gId = gId;
        this.vId = vId;
        this.orderNum = orderNum;
        this.money = money;
        this.name = name;
        this.tel = tel;
        this.wx = wx;
        this.orderStatus = orderStatus;
        this.orderProgress = orderProgress;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.goDate = goDate;
    }
}
