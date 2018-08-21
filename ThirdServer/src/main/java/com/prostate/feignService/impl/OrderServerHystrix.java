package com.prostate.feignService.impl;

import com.prostate.feignService.OrderServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {

    @Override
    public Map<String, Object> getOrder(String orderId) {
        return resultMap;
    }
}
