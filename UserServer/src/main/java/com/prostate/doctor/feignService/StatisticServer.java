package com.prostate.doctor.feignService;


import com.prostate.doctor.feignService.impl.StatisticServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "statistic-server", fallback = StatisticServerHystrix.class)
public interface StatisticServer {

    @PostMapping(value = "statistic/addOrderSuccess")
    Map<String, Object> addOrderSuccess(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addDoctorFocus")
    Map<String, Object> addDoctorFocus(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addPatientFocus")
    Map<String, Object> addPatientFocus(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addDoctorClick")
    Map<String, Object> addDoctorClick(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addPatientClick")
    Map<String, Object> addPatientClick(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addDoctorUnFocus")
    Map<String, Object> addDoctorUnFocus(@RequestParam("userId") String userId);

    @PostMapping(value = "statistic/addPatientUnFocus")
    Map<String, Object> addPatientUnFocus(@RequestParam("userId") String userId);

    @GetMapping(value = "statistic/getDoctorCount")
    Map<String, Object> getDoctorCount(@RequestParam("userId") String userId);
}
