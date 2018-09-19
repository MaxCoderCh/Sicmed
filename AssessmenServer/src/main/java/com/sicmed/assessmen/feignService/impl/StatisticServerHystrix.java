package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.StatisticServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StatisticServerHystrix extends BaseServerHystrix implements StatisticServer {

    @Override
    public String addTotleIncome(String userId, String orderPrice) {
        return resultStr;
    }
}
