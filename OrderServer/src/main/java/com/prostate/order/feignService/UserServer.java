package com.prostate.order.feignService;


import com.prostate.order.feignService.impl.UserServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server", fallback = UserServerHystrix.class)
public interface UserServer {

    @GetMapping(value = "user/weChat/getOpenid")
    String getOpenid(@RequestParam("userId") String userId);

}
