package com.prostate.order.feignService.impl;

import com.prostate.order.feignService.WalletServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WalletServerHystrix extends BaseServerHystrix implements WalletServer {

    @Override
    public Map cashOrder(String orderId) {
        return resultMap;
    }
}
