package com.prostate.record.feignService;

import com.prostate.record.feignService.impl.OrderServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "order-server", fallback = OrderServerHystrix.class)
public interface OrderServer {

    @PostMapping(value = "order/inquiry/getOrder")
    Map<String,Object> getOrder(@RequestParam("orderId") String orderId);
}
