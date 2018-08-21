package com.sicmed.assessmen.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sicmed.assessmen.entity.Doctor;
import com.sicmed.assessmen.entity.WechatUser;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils<E> {

    /**
     * java普通对象和json字符串的互转
     */

    public String objectToJsonStr(Doctor doctor){
        return JSONObject.toJSONString(doctor);
    }

    public Doctor jsonStrToObject(String jsonStr){
        return JSON.parseObject(jsonStr, Doctor.class);

    }
    public WechatUser jsonStrToWechatUser(String jsonStr){
        return JSON.parseObject(jsonStr, WechatUser.class);

    }
}
