package com.sicmed.statistic.feignService.impl;


import com.sicmed.statistic.feignService.OrderServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {
    @Override
    public Map<String,Object> getOrder(String orderId) {
        return resultMap;
    }

    @Override
    public String getAcceptedOrderCount(String userId) {
        return "0";
    }

    @Override
    public String getAcceptedTurnOrderCount(String userId) {
        return "0";
    }
}
