package com.sicmed.statistic.feignService.impl;


import com.sicmed.statistic.feignService.OrderServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {
    @Override
    public Map getOrder(String orderId) {
        return BaseServerHystrix.resultMap;
    }
}
