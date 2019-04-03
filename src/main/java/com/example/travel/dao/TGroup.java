package com.example.travel.dao;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity(name="t_group")
@Data
@DynamicUpdate
public class TGroup {
    @Id
    @GeneratedValue
    private Integer groupId;
    private String groupNumber;
    private Integer hoId;
    private Integer gId;
    private Integer tId;
    private  Integer tNumUp;
    private Integer tNum;
    private  Integer payNum;
    private Timestamp createDate;
    private Timestamp updateDate;
    private Date goTime;
    private String status;
    private String verificationCode;

    public TGroup() {
    }

    public TGroup(String groupNumber, Integer hoId, Integer gId, Integer tId, Integer tNumUp, Integer tNum, Integer payNum, Timestamp createDate, Timestamp updateDate, Timestamp goTime, String status, String verificationCode) {
        this.groupNumber = groupNumber;
        this.hoId = hoId;
        this.gId = gId;
        this.tId = tId;
        this.tNumUp = tNumUp;
        this.tNum = tNum;
        this.payNum = payNum;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.goTime = goTime;
        this.status = status;
        this.verificationCode = verificationCode;
    }
}
