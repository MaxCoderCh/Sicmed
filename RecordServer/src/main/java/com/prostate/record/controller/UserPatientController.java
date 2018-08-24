package com.prostate.record.controller;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;
import com.prostate.record.feignService.OrderServer;
import com.prostate.record.service.PatientService;
import com.prostate.record.service.UserPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "userPatient")
public class UserPatientController extends BaseController {

    @Autowired
    private UserPatientService userPatientService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private OrderServer orderServer;
    /**
     * 添加医生 患者 关系
     *
     * @param patientId
     * @param userId
     * @param patientSource
     * @return
     */
    @PostMapping(value = "addUserPatient")
    public Map<String, Object> addUserPatient(String patientId, String userId, String patientSource) {
        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(userId);
        userPatient.setPatientId(patientId);

        List<UserPatient> userPatientList = userPatientService.selectByParams(userPatient);

        if (userPatientList == null || userPatientList.isEmpty()) {
            int i = userPatientService.addUserPatient(patientId, userId, patientSource);
            if (i > 0) {
                return insertSuccseeResponse("添加成功");
            }
            return insertFailedResponse("添加失败");
        }
        return insertalreadyExistedResponse("记录已经存在");
    }

    /**
     * 查询 患者列表
     */

    @GetMapping(value = "getPatientList")
    public Map getPatientList(){

        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(getToken());

        List<WeChatPatientBean> weChatPatientBeanList = userPatientService.getPatientList(userPatient);

        if (weChatPatientBeanList == null || weChatPatientBeanList.isEmpty()){
            return queryEmptyResponse();
        }
        return querySuccessResponse(weChatPatientBeanList);
    }

    /**
     * 移除就诊人
     * @param patientId
     * @return
     */
    @PostMapping(value = "remove")
    public Map remove(String patientId){

        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(getToken());
        userPatient.setPatientId(patientId);
        int i = userPatientService.removeByParams(userPatient);

        if (i > 0){
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }

    /**
     * 修改就诊人关系
     * @param patientId
     * @return
     */
    @PostMapping(value = "update")
    public Map update(String patientId,String patientSource){

        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(getToken());
        userPatient.setPatientId(patientId);
        userPatient.setPatientSource(patientSource);
        int i = userPatientService.updateByParams(userPatient);

        if (i > 0){
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }



    /**
     * 查询 患者列表
     */

    @GetMapping(value = "getPatientListById")
    public Map getPatientListById(String userId){

        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(userId);

        List<WeChatPatientBean> weChatPatientBeanList = userPatientService.getPatientList(userPatient);

        if (weChatPatientBeanList == null || weChatPatientBeanList.isEmpty()){
            return queryEmptyResponse();
        }
        return querySuccessResponse(weChatPatientBeanList);
    }


    /**
     * 订单完成 添加 医生 患者 关系
     */
    @PostMapping(value = "addUserPatientByOrder")
    public String addUserPatientByOrder(String orderId) {

        //查询订单 信息
        Map<String, Object> orderResultMap = orderServer.getOrder(orderId);
        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");
        //插入对象 赋值
        UserPatient userPatient = new UserPatient();

        userPatient.setUserId(orderMap.get("doctor").toString());
        userPatient.setPatientId(orderMap.get("patient").toString());
        String orderType = orderMap.get("orderType").toString();
        if ("".equals(orderType)) {
            userPatient.setPatientSource("问诊");
        } else {
            userPatient.setPatientSource("转诊");
        }
        //重复记录校验
        int i = userPatientService.selectCountByParams(userPatient);
        if (i > 0) {
            return "ERROR";
        }
        //添加记录
        i = userPatientService.insertSelective(userPatient);
        if (i > 0) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    /**
     * 转诊 患者
     */
    @PostMapping(value = "turnPatient")
    public Map<String, Object> turnPatient(String patientId, String doctorId) {
        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(doctorId);
        userPatient.setPatientId(patientId);

        int i = userPatientService.selectCountByParams(userPatient);
        if (i > 0) {
            return insertSuccseeResponse("转诊成功");
        }
        userPatient.setPatientSource("转诊");
        i = userPatientService.insertSelective(userPatient);
        if (i > 0) {
            return insertSuccseeResponse("转诊成功");
        }
        return insertFailedResponse("转诊失败");
    }
}



