package com.sicmed.statistic.feignService;


import com.sicmed.statistic.feignService.impl.RecordServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "record-server",fallback = RecordServerHystrix.class)
public interface RecordServer {

    @GetMapping(value = "userPatient/getAcceptedTurnPatientCount")
    String getAcceptedTurnPatientCount(@RequestParam("userId") String userId);

}
