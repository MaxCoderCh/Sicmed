package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.RecordServer;
import org.springframework.stereotype.Component;

@Component
public class RecordServerHystrix extends BaseServerHystrix implements RecordServer {

    @Override
    public String addUserPatientByOrder(String userId, String patientId, String orderType) {
        return resultStr;
    }
}
