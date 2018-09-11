package com.prostate.order.feignService;

import com.prostate.order.feignService.impl.ThirdServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "third-server",fallback = ThirdServerHystrix.class)
public interface ThirdServer {

    @PostMapping(value = "sms/sendPaymentSuccess")
    Map<String, Object> sendPaymentSuccess(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendInquiryEndToPatient")
    Map<String, Object> sendInquiryEndToPatient(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendInquiryEndToDoctor")
    Map<String, Object> sendInquiryEndToDoctor(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendInquiryRefund")
    Map<String, Object> sendInquiryRefund(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendProveSuccess")
    Map<String, Object> sendProveSuccess(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendProveFailed")
    Map<String, Object> sendProveFailed(@RequestParam("phoneNumber") String phoneNumber);

    @PostMapping(value = "sms/sendBalanceForCash")
    Map<String, Object> sendBalanceForCash(@RequestParam("phoneNumber") String phoneNumber);
}
