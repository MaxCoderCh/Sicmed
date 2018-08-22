package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.RecordServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "record-server", fallback = RecordServerHystrix.class)
public interface RecordServer {

    @PostMapping(value = "userPatient/addUserPatient")
    String addUserPatient(String orderId);
}
