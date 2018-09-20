package com.prostate.record.feignService.impl;

import com.prostate.record.feignService.ThirdServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThirdServerHystrix extends BaseServerHystrix implements ThirdServer {


    @Override
    public Map<String, Object> idCard(String url) {
        return resultMap;
    }
}
