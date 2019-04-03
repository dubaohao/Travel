package com.example.travel.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "g_user")
@Data
public class GUser {
    @Id
    @GeneratedValue
    private Integer gId;
    private String username;
    private String password;
    private String age;
    private String tel;
    private String description;
    private String wxappid;
    private String company;
    private Integer money;

    public GUser() {
    }

    public GUser(String username, String password, String age, String tel, String description, String wxappid, String company, Integer money) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.tel = tel;
        this.description = description;
        this.wxappid = wxappid;
        this.company = company;
        this.money = money;
    }
}
