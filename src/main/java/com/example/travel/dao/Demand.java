package com.example.travel.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="demand")
@Data
public class Demand {
    @Id
    @GeneratedValue
    private Integer demandId;
    private String demandTime;
    private Integer demandDay;
    private String demandWhere;
    private Integer demandNumber;
    private  Integer demandMoney;
    private Integer gId;
    private String way;
    private String other;
}
