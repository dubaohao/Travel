package com.example.travel.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="v_user")
@Data
public class VUser {
    @Id
    @GeneratedValue
    private Integer vId;
    private String username;
    private String password;
    private String age;
    private String tel;
    private String description;
    private String wxappid;
    private Integer money;

    public VUser() {
    }

    public VUser(String username, String password, String age, String tel, String description, String wxappid, Integer money) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.tel = tel;
        this.description = description;
        this.wxappid = wxappid;
        this.money = money;
    }
}
