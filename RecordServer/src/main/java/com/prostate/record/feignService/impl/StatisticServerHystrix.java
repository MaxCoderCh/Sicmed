package com.prostate.record.feignService.impl;

import com.prostate.record.feignService.StatisticServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StatisticServerHystrix extends BaseServerHystrix implements StatisticServer {

    @Override
    public Map<String, Object> addOrderSuccess(String userId) {
        return resultMap;
    }
}
