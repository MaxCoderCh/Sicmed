package com.sicmed.assessmen.feignService.impl;

import com.sicmed.assessmen.feignService.FileServer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FileServerHystrix extends BaseServerHystrix implements FileServer {

    @Override
    public Map<String, Object> delete(String imgPath) {
        return resultMap;
    }
}
