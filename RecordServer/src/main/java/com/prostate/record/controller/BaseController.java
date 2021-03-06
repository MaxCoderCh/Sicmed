package com.prostate.record.controller;


import com.prostate.record.cache.redis.RedisSerive;
import com.prostate.record.feignService.ThirdServer;
import com.prostate.record.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

public class BaseController {

    public  Map<String,Object> resultMap;

    @Autowired
    protected RedisSerive redisSerive;

    @Autowired
    protected FeignService feignService;

    public String getToken() {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        return request.getHeader("token");
    }
    /**
     * 参数为空返回值
     * @return
     */
    public Map<String, Object> emptyParamResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20001");
        resultMap.put("msg","EMPTY_PARAM");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求成功返回值
     * @param result
     * @return
     */
    public Map<String, Object> querySuccessResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     * @param result
     * @return
     */
    public Map<String, Object> querySuccessResponse(Object result,String count){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","SUCCESS");
        resultMap.put("result",result);
        resultMap.put("count",count);
        return resultMap;
    }
    /**
     * 查询请求结果为空返回值
     * @return
     */
    public Map<String, Object> queryEmptyResponse(){
        resultMap = new LinkedHashMap<>();

        resultMap.put("code","40004");
        resultMap.put("msg","RESULT_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 请求失败返回值
     * @return
     */
    public Map<String, Object> failedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50002");
        resultMap.put("msg","FAILED_EMPTY");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     * @return
     */
    public Map<String, Object> insertFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50003");
        resultMap.put("msg","INSERT_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * INSERT 失败返回值
     *
     * @return
     */
    public Map<String, Object> insertFailedResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     * @return
     */
    public Map<String, Object> insertSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }
    /**
     * 插入请求成功返回值
     * @return
     */


    public Map<String, Object> insertSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","INSERT_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 插入的数据已经存在
     * @param result
     * @return
     */
    public Map<String, Object> insertalreadyExistedResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50012");
        resultMap.put("msg","ALREADY_EXISTED");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @return
     */
    public Map<String, Object> updateSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     * @param result
     * @return
     */
    public Map<String, Object> updateSuccseeResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","UPDATE_SUCCESS");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     * @return
     */
    public Map<String, Object> updateFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50004");
        resultMap.put("msg","UPDATE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }


    /**
     * DELETE成功返回值
     * @return
     */
    public Map<String, Object> deleteSuccseeResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","20000");
        resultMap.put("msg","DELETE_SUCCESS");
        resultMap.put("result",null);
        return resultMap;
    }


    /**
     * DELETE请求失败返回值
     * @return
     */
    public Map<String, Object> deleteFailedResponse(){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","50005");
        resultMap.put("msg","DELETE_FAILED");
        resultMap.put("result",null);
        return resultMap;
    }

    /**
     * 请求失败 返回值
     * @return
     */
    public Map<String, Object> requestFailedResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","60001");
        resultMap.put("msg","REQUEST_FAILED");
        resultMap.put("result",result);
        return resultMap;
    }

    /**
     * 转诊已存在
     * @param result
     * @return
     */
    public Map<String, Object> turnPatientExistResponse(Object result){
        resultMap = new LinkedHashMap<>();
        resultMap.put("code","60002");
        resultMap.put("msg","TURN_EXIST");
        resultMap.put("result",result);
        return resultMap;
    }
}
