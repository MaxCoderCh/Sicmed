package com.prostate.assessmen.cache.redis;

import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.WechatUser;
import com.prostate.assessmen.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author MaxCoder
 *
 * @date 2017.04.09
 *
 * Redis 服务
 */
@Service
public class RedisSerive {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JsonUtils<Doctor> jsonUtil;

    public void insert(String key,String val) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,val);
    }

    public String get(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public Doctor getDoctor(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return jsonUtil.jsonStrToObject(valueOperations.get(key));
    }

    public boolean remove(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return stringRedisTemplate.delete(key);
    }

    public WechatUser getWechatUser(String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return jsonUtil.jsonStrToWechatUser(valueOperations.get(key));
    }
}
