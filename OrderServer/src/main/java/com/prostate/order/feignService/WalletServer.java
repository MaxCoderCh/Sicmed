package com.prostate.order.feignService;


import com.prostate.order.feignService.impl.WalletServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "wallet-server", fallback = WalletServerHystrix.class)
public interface WalletServer {

    @PostMapping(value = "provider/cashOrder")
    Map cashOrder(@RequestParam("orderId") String orderId);
}
