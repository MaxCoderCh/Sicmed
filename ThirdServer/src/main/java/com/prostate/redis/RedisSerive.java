package com.prostate.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author MaxCoder
 * @date 2017.04.09
 * <p>
 * Redis 服务
 */
@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 添加一条缓存数据
     * @param key
     * @param val
     * @param l 过期时间 秒
     */
    public void insert(String key, String val, long l) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, val, l, TimeUnit.SECONDS);
    }

    public String getOpenid() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getHeader("token");
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        String jsonStr = valueOperations.get(token);
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return jsonObject.get("openid").toString();
    }

    public String getAccessToken() {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        return valueOperations.get("ACCESS_TOKEN");
    }

    public void setAccessToken(String accessToken) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("ACCESS_TOKEN", accessToken, 7100, TimeUnit.SECONDS);
    }

    public String getOutRefundNo(String orderId) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        return valueOperations.get(orderId);
    }


    public void removeOutRefundNo(String orderId) {
        stringRedisTemplate.delete(orderId);
    }
}
