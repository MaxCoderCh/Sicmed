package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.OrderServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {
    @Override
    public  Map<String,Object> orderDoneSuccess(String orderId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> getOrder(String orderId) {
        return resultMap;
    }
}
