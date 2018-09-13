package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.UserServer;
import org.springframework.stereotype.Component;


@Component
public class UserServerHystrix extends BaseServerHystrix implements UserServer {


    @Override
    public String getPhoneNumber(String userId) {
        return "ERROR";
    }

    @Override
    public String getOpenid(String userId) {
        return "ERROR";
    }
}
