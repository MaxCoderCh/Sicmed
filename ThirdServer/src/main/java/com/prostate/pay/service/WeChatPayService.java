package com.prostate.pay.service;


import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WeChatPayService {

    //下单
    Map<String,String> unifiedOrder(Map<String, String> reqDate);

    //下单
    Map<String,String> refund(Map<String, String> reqDate) throws Exception;
}
