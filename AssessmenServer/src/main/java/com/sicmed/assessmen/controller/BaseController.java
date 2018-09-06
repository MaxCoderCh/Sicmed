package com.sicmed.assessmen.controller;


import com.sicmed.assessmen.feignService.OrderServer;
import com.sicmed.assessmen.feignService.RecordServer;
import com.sicmed.assessmen.feignService.StatisticServer;
import com.sicmed.assessmen.feignService.WalletServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

public class BaseController {

    public LinkedHashMap<String, Object> resultMap;

    @Autowired
    protected OrderServer orderServer;

    @Autowired
    protected RecordServer recordServer;

    @Autowired
    protected WalletServer walletServer;

    @Autowired
    protected StatisticServer statisticServer;

    protected String getToken() {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();

        return request.getHeader("token");
    }
    /**
     * 参数为空返回值
     *
     * @return
     */
    public LinkedHashMap emptyParamResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20001");
        resultMap.put("msg", "EMPTY_PARAM");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求成功返回值
     *
     * @param result
     * @return
     */
    public LinkedHashMap querySuccessResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * 分页查询请求成功返回值
     *
     * @param result
     * @return
     */
    public LinkedHashMap querySuccessResponse(Object result, String count) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "SUCCESS");
        resultMap.put("result", result);
        resultMap.put("count", count);
        return resultMap;
    }

    /**
     * 查询请求结果为空返回值
     *
     * @return
     */
    public LinkedHashMap queryEmptyResponse() {
        resultMap = new LinkedHashMap<>();

        resultMap.put("code", "40004");
        resultMap.put("msg", "RESULT_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public LinkedHashMap failedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50002");
        resultMap.put("msg", "FAILED_EMPTY");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public LinkedHashMap insertFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 请求失败返回值
     *
     * @return
     */
    public LinkedHashMap insertFailedResponse(Object obj) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50003");
        resultMap.put("msg", "INSERT_FAILED");
        resultMap.put("result", obj);
        return resultMap;
    }

    /**
     * 重复添加返回
     *
     * @return
     */
    public LinkedHashMap insertRepeatResponse(Object obj) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50005");
        resultMap.put("msg", "INSERT_REPEAT");
        resultMap.put("result", obj);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */
    public LinkedHashMap insertSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * 插入请求成功返回值
     *
     * @return
     */

    public LinkedHashMap insertSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "INSERT_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @return
     */
    public LinkedHashMap updateSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * UPDATE成功返回值
     *
     * @param result
     * @return
     */
    public LinkedHashMap updateSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "UPDATE_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * UPDATE请求失败返回值
     *
     * @return
     */
    public LinkedHashMap updateFailedResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50004");
        resultMap.put("msg", "UPDATE_FAILED");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * DELETE成功返回值
     *
     * @param result
     * @return
     */
    public LinkedHashMap deleteSuccseeResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "DELETE_SUCCESS");
        resultMap.put("result", result);
        return resultMap;
    }

    /**
     * DELETE成功返回值
     *
     * @return
     */
    public LinkedHashMap deleteSuccseeResponse() {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "20000");
        resultMap.put("msg", "DELETE_SUCCESS");
        resultMap.put("result", null);
        return resultMap;
    }

    /**
     * DELETE失败返回值
     *
     * @param result
     * @return
     */
    public LinkedHashMap deleteFailedResponse(Object result) {
        resultMap = new LinkedHashMap<>();
        resultMap.put("code", "50006");
        resultMap.put("msg", "DELETE_Failed");
        resultMap.put("result", result);
        return resultMap;
    }
}
