package com.sicmed.statistic.feignService;


import com.sicmed.statistic.feignService.impl.OrderServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "order-server",fallback = OrderServerHystrix.class)
public interface OrderServer {

    @GetMapping(value = "order/inquiry/getAcceptedOrderCount")
    String getAcceptedOrderCount(@RequestParam("userId") String userId);

    @GetMapping(value = "order/inquiry/getAcceptedTurnOrderCount")
    String getAcceptedTurnOrderCount(@RequestParam("userId")String userId);
}
