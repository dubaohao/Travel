package com.example.travel.utils;

import java.util.Random;

public class RandomNumberUtil {
    public static synchronized String orderNumber(){
        Random random =new Random();
        Integer number=random.nextInt(900000)+100000;

        return "DD"+System.currentTimeMillis()+String.valueOf(number);
    }
    public static String groupNumber(){
        Random random =new Random();
        Integer number=random.nextInt(900000)+100000;

        return "DD"+System.currentTimeMillis()+String.valueOf(number);
    }
}