package com.example.travel.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
@Slf4j
public class WeChatParamUtil {
    /**
     * @param
     * @throws Exception
     */
    public static String ip(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString(); //获取本机ip
//        String hostName=addr.getHostName().toString(); //获取本机计算机名称
            System.out.println("IP:" + ip);
//        System.out.println(hostName);
//            log.info("WeChatParamUtil获取IP:{}",ip);
            return ip;
        }catch (Exception e){
            log.error("WeChatParamUtil获取IP失败！原因:{}", e.getMessage());
        }
        return "127.0.0.1";
    }

}
