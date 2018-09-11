package com.sicmed.statistic.feignService.impl;


import com.sicmed.statistic.feignService.RecordServer;
import org.springframework.stereotype.Component;



@Component
public class RecordServerHystrix extends BaseServerHystrix implements RecordServer {

    @Override
    public String getAcceptedTurnPatientCount(String orderId) {
        return "0";
    }
}
