package com.sicmed.assessmen.feignService;

import com.sicmed.assessmen.feignService.impl.ThirdServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "third-server",fallback = ThirdServerHystrix.class)
public interface ThirdServer {

    @PostMapping(value = "sms/sendInquiryEndToDoctor")
    String sendInquiryEndToDoctor(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "push/weChat/pushOrderSuccessToWechat")
    String pushOrderSuccessToWechat(@RequestParam("openid") String openid, @RequestParam("orderId") String orderId, @RequestParam("finishTime") String finishTime);
}
