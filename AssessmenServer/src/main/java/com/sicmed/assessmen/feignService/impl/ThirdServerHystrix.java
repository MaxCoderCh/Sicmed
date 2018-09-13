package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.ThirdServer;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class ThirdServerHystrix extends BaseServerHystrix implements ThirdServer {

    @Override
    public String pushOrderSuccessToWechat(String openid, String orderId, String finishTime) {
        return "ERROR";
    }

    @Override
    public String sendInquiryEndToDoctor(String phoneNumber) {
        return "ERROR";
    }
}
