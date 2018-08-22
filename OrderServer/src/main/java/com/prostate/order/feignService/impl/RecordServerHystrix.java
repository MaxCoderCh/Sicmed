package com.prostate.order.feignService.impl;

import com.prostate.order.feignService.RecordServer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RecordServerHystrix extends BaseServerHystrix implements RecordServer {

    @Override
    public Map getPatientListById(String userId) {
        return resultMap;
    }

    @Override
    public Map<String, Object> getPatientListByIds(String patientIds) {
        return resultMap;
    }

}
