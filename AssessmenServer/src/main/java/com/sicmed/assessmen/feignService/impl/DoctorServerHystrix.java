package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.DoctorServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DoctorServerHystrix extends BaseServerHystrix implements DoctorServer {
    @Override
    public Map<String, Object> getone() {

        return resultMap;
    }


}
