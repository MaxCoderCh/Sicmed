package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.WalletServer;
import org.springframework.stereotype.Component;

@Component
public class WalletServerHystrix extends BaseServerHystrix implements WalletServer {
}
