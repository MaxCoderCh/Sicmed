package com.prostate.stata.feignService.impl;

import com.prostate.stata.feignService.DoctorServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DoctorServerHystrix extends BaseServerHystrix implements DoctorServer {
    @Override
    public Map<String, Object> getone() {

        return resultMap;
    }


}
