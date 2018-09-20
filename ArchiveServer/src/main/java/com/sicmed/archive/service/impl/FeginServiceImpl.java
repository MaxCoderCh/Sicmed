package com.sicmed.archive.service.impl;

import com.sicmed.archive.feignService.ThirdServer;
import com.sicmed.archive.service.FeginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class FeginServiceImpl implements FeginService {

    @Autowired
    private ThirdServer thirdServer;

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 2))
    public void notifyThirdServerDeleteFile(String imgPath) throws Exception {

        Map<String, Object> serverResult = thirdServer.delete(imgPath);
        String resultCode = String.valueOf(serverResult.get("code"));
        if ("20000".equals(resultCode)) {
            log.info("调用 ThirdServer 删除文件:" + imgPath + "成功!");
        } else {
            log.error("调用 ThirdServer 删除文件:" + imgPath + "失败!");
            throw new Exception("RPC调用异常");
        }
    }
}
