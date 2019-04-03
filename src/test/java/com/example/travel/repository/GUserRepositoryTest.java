package com.example.travel.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GUserRepositoryTest {
    @Autowired
    private GUserRepository repository;

    @Test
    public void find(){
        System.out.println(repository.findOne(1));
        return;
}

}