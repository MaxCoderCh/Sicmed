package com.prostate.doctor.feignService.impl;

import com.prostate.doctor.feignService.StatisticServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StatisticServerHystrix extends BaseServerHystrix implements StatisticServer {
    @Override
    public Map<String, Object> addOrderSuccess(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addDoctorFocus(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addPatientFocus(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addDoctorClick(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addPatientClick(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addDoctorUnFocus(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> addPatientUnFocus(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> getDoctorCount(String userId) {
        return resultMap;
    }
}
