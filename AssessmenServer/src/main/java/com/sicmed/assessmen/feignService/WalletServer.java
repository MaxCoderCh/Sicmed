package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.WalletServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "wallet-server", fallback = WalletServerHystrix.class)
public interface WalletServer {

    @PostMapping(value = "provider/addOrderIncome")
    String addOrderIncome(@RequestParam("orderId") String orderId,@RequestParam("doctorId") String doctorId,@RequestParam("orderPrice") String orderPrice);
}
