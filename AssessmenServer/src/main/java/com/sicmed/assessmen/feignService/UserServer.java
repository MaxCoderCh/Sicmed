package com.sicmed.assessmen.feignService;


import com.sicmed.assessmen.feignService.impl.UserServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-server", fallback = UserServerHystrix.class)
public interface UserServer {

    @GetMapping(value = "doctor/getPhoneNumber")
    String getPhoneNumber(@RequestParam("userId") String userId);

    @GetMapping(value = "user/weChat/getOpenid")
    String getOpenid(@RequestParam("userId") String userId);

}
