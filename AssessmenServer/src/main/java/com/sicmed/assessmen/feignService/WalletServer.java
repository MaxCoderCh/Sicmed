package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.WalletServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "wallet-server", fallback = WalletServerHystrix.class)
public interface WalletServer {

    @PostMapping(value = "doctorWallet/addOrderIncome")
    Map addOrderIncome(String orderId);
}