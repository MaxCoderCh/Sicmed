package com.prostate.order.feignService.impl;

import com.prostate.order.feignService.UserServer;
import org.springframework.stereotype.Component;


@Component
public class UserServerHystrix extends BaseServerHystrix implements UserServer {

    @Override
    public String getOpenid(String userId) {
        return "ERROR";
    }
}
