package com.prostate.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public String getOpenid() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String jsonStr = valueOperations.get(token);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return String.valueOf(jsonObject.get("openid"));
    }

}
