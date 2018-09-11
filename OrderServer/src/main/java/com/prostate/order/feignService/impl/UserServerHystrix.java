package com.prostate.order.feignService.impl;

import com.prostate.order.feignService.UserServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserServerHystrix extends BaseServerHystrix implements UserServer {


    @Override
    public Map getPhoneNumber(String userId) {
        return resultMap;
    }

    @Override
    public Map getOpenid(String userId) {
        return resultMap;
    }
}
