package com.example.travel.dao;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue
    private  Integer cId;
    private  Integer gId;
    private  String category;

    public Category() {
    }

    public Category(Integer gId, String category) {
        this.gId = gId;
        this.category = category;
    }
}
