package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.StatisticServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "statistic-server", fallback = StatisticServerHystrix.class)
public interface StatisticServer {

    @PostMapping(value = "statistic/addTotleIncome")
    String addTotleIncome(@RequestParam("orderId") String orderId);


}
