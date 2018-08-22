package com.sicmed.assessmen.feignService.impl;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseServerHystrix {

    public static Map<String,Object> resultMap;
    public static String resultStr;

    public BaseServerHystrix(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50000");
        resultMap.put("msg","ERROR");
        resultMap.put("result","调用服务失败");
        resultStr = "ERROR";
    }
}
