package com.prostate.order.feignService;


import com.prostate.order.feignService.impl.UserServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(value = "user-server", fallback = UserServerHystrix.class)
public interface UserServer {

    @GetMapping(value = "doctor/getPhoneNumber")
    Map getPhoneNumber(@RequestParam("userId") String userId);

    @GetMapping(value = "user/weChat/getOpenid")
    Map getOpenid(@RequestParam("userId") String userId);

}
