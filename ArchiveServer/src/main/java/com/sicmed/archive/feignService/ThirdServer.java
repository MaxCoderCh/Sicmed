package com.sicmed.archive.feignService;

import com.sicmed.archive.feignService.impl.ThirdServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author MaxCoder
 */
@FeignClient(value = "third-server",fallback = ThirdServerHystrix.class)
public interface ThirdServer {

    /**
     *
     * @param imgPath
     * @return
     */
    @PostMapping(value = "cos/delete")
    Map<String, Object> delete(@RequestParam("imgPath") String imgPath);

}
