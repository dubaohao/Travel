package com.example.travel.configWeChatSDK;

import com.example.travel.config.WechatAccountConfig;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.github.binarywang.wxpay.service.WxPayService;
import java.io.*;

//@Component
@Service
public class MyConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxPayServiceImpl wxPayService(){
        WxPayConfig payConfig = new WxPayConfig();

        payConfig.setAppId(wechatAccountConfig.getMpAppId());
        payConfig.setMchId(wechatAccountConfig.getMchId());
        payConfig.setMchKey(wechatAccountConfig.getMchKey());
        payConfig.setKeyPath(wechatAccountConfig.getKeyPath());
        payConfig.setNotifyUrl(wechatAccountConfig.getNotifyUrl());

        WxPayServiceImpl wxPayService =new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }




}