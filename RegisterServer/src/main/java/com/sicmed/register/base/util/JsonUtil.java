package com.sicmed.register.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil<E> {

    /**
     * java普通对象和json字符串的互转
     */

    public String objectToJsonStr(E e){
        return JSONObject.toJSONString(e);
    }

    public Object jsonStrToObject(String jsonStr){
        return JSON.parseObject(jsonStr, Object.class);

    }

    public Object jsonStrToWechatUser(String jsonStr){
        return JSON.parseObject(jsonStr, Object.class);

    }
}
