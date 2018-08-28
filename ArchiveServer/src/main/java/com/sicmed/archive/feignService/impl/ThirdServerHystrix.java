package com.sicmed.archive.feignService.impl;

import com.sicmed.archive.feignService.ThirdServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThirdServerHystrix extends BaseServerHystrix implements ThirdServer {


    @Override
    public Map<String, Object> delete(String imgPath) {
        return resultMap;
    }
}
