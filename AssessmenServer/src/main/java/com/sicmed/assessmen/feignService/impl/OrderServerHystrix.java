package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.OrderServer;
import org.springframework.stereotype.Component;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {
    @Override
    public String orderDoneSuccess(String orderId) {
        return resultStr;
    }
}
