package com.prostate.assessmen.feignService;

import com.prostate.assessmen.feignService.impl.FileServerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用 DoctorServer 中的 API
 */
@FeignClient(value = "file-server",fallback = FileServerHystrix.class)
public interface FileServer {

    @PostMapping(value = "/file/delete")
    Map<String,Object> delete(@RequestParam("imgPath") String imgPath);

}
