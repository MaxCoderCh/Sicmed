package com.prostate.doctor.feignService.impl;

import com.prostate.doctor.feignService.StaticServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StaticServerHystrix extends BaseServerHystrix implements StaticServer {

    @Override
    public Map getHospitalById(String id) {
        return resultMap;
    }

    @Override
    public Map getBranchById(String id) {
        return resultMap;
    }

    @Override
    public Map getTitleById(String id) {
        return resultMap;
    }

    @Override
    public Map<String, Object> hospitalJson() {
        return resultMap;
    }

    @Override
    public Map<String, Object> branchServiceJson() {
        return resultMap;
    }

    @Override
    public Map<String, Object> doctorTitleJson() {
        return resultMap;
    }
}
