package com.sicmed.assessmen.feignService;

import com.sicmed.assessmen.feignService.impl.OrderServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order-server",fallback = OrderServerHystrix.class)
public interface OrderServer {

    @PostMapping(value = "order/notify/orderDoneSuccess")
    String orderDoneSuccess(@RequestParam("orderId") String orderId);
}
