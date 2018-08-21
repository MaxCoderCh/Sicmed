package com.prostate.order.feignService;

import com.prostate.order.feignService.impl.RecordServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "record-server", fallback = RecordServerHystrix.class)
public interface RecordServer {

    @GetMapping(value = "userPatient/getPatientListById")
    Map getPatientListById(@RequestParam("userId") String userId);
}
