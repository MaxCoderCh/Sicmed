package com.prostate.record.feignService;


import com.prostate.record.feignService.impl.StatisticServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "statistic-server", fallback = StatisticServerHystrix.class)
public interface StatisticServer {

    @PostMapping(value = "statistic/addOrderSuccess")
    Map<String, Object> addOrderSuccess(@RequestParam("userId") String userId);


}
