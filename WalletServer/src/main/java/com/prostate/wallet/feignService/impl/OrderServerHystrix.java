package com.prostate.wallet.feignService.impl;


import com.prostate.wallet.feignService.OrderServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServerHystrix extends BaseServerHystrix implements OrderServer {
    @Override
    public Map<String,Object> getOrder(String orderId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> getOrderCash(String orderId) {
        return resultMap;
    }
}
