package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.WalletServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WalletServerHystrix extends BaseServerHystrix implements WalletServer {
    @Override
    public String addOrderIncome(String orderId) {
        return "ERROR";
    }
}
