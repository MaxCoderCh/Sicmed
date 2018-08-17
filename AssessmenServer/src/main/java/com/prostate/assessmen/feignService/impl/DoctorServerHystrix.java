package com.prostate.assessmen.feignService.impl;

import com.prostate.assessmen.feignService.DoctorServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DoctorServerHystrix extends BaseServerHystrix implements DoctorServer {
    @Override
    public Map<String, Object> getone() {

        return resultMap;
    }


}
