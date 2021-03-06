package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.RecordServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "record-server", fallback = RecordServerHystrix.class)
public interface RecordServer {

    @PostMapping(value = "userPatient/addUserPatientByOrder")
    String addUserPatientByOrder(@RequestParam("userId") String userId,@RequestParam("patientId") String patientId,@RequestParam("orderType")String orderType);
}
