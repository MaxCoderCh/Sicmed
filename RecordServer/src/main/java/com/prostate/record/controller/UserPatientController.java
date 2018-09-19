package com.prostate.record.controller;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;
import com.prostate.record.feignService.OrderServer;
import com.prostate.record.feignService.StatisticServer;
import com.prostate.record.service.PatientService;
import com.prostate.record.service.UserPatientService;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private StatisticServer statisticServer;
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

        userPatient.setPatientStatus("TO_BE_ACCEPTED");
        userPatient.setPatientSource("转诊");

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
     * 查询 带接受 转诊 患者列表
     */

    @GetMapping(value = "getAcceptedTurnPatientList")
    public Map getAcceptedTurnPatientList() {

        UserPatient userPatient = new UserPatient();
        userPatient.setUserId(getToken());
        userPatient.setPatientStatus("TO_BE_ACCEPTED");
        List<WeChatPatientBean> weChatPatientBeanList = userPatientService.getAcceptedTurnPatientList(userPatient);

        if (weChatPatientBeanList == null || weChatPatientBeanList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(weChatPatientBeanList);
    }

    /**
     * 查询 带接受 转诊 患者数量
     */

    @GetMapping(value = "getAcceptedTurnPatientCount")
    public String getAcceptedTurnPatientCount(String userId) {

        UserPatient userPatient = new UserPatient();

        userPatient.setUserId(userId);
        userPatient.setPatientStatus("TO_BE_ACCEPTED");
        String acceptedTurnPatientCount = userPatientService.countByParams(userPatient);

        if (StringUtils.isBlank(acceptedTurnPatientCount)) {
            return "0";
        }
        return acceptedTurnPatientCount;
    }

    /**
     * 同意 接受转诊 患者
     */

    @PostMapping(value = "acceptedPatient")
    public Map acceptedPatient(String id) {

        UserPatient userPatient = new UserPatient();
        userPatient.setId(id);
        userPatient.setPatientStatus("BE_ACCEPTED");
        int i = userPatientService.updateSelective(userPatient);

        if (i > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
    }

    /**
     * 拒绝 接受转诊 患者
     */

    @PostMapping(value = "rejectedPatient")
    public Map rejectedPatient(String id) {

        UserPatient userPatient = new UserPatient();
        userPatient.setId(id);
        userPatient.setPatientStatus("BE_REJECTED");
        int i = userPatientService.updateSelective(userPatient);

        if (i > 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();
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
        userPatient = userPatientService.getByPatientIdAndToken(userPatient);
        int i;
        if (userPatient != null) {
            userPatient.setPatientSource(patientSource);
            i = userPatientService.updateByParams(userPatient);
        } else {
            userPatient = new UserPatient();

            userPatient.setUserId(getToken());
            userPatient.setPatientId(patientId);
            userPatient.setPatientSource(patientSource);

            i = userPatientService.insertSelective(userPatient);
        }

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
    public String addUserPatientByOrder(String userId,String patientId,String orderType) {

        //查询订单 信息
//        Map<String, Object> orderResultMap = orderServer.getOrder(orderId);
//        Map<String, Object> orderMap = (Map<String, Object>) orderResultMap.get("result");
        //插入对象 赋值
        UserPatient userPatient = new UserPatient();

//        String userId = orderMap.get("doctor").toString();
        userPatient.setUserId(userId);
        userPatient.setPatientId(patientId);
//        String orderType = orderMap.get("orderType").toString();
        if ("PICTURE_INQUIRY_TYPE".equals(orderType)) {
            userPatient.setPatientSource("问诊");
        } else {
            userPatient.setPatientSource("转诊");
        }
        //重复记录校验
        int i = userPatientService.selectCountByParams(userPatient);
        if (i > 0) {
            return "SUCCESS";
        }
        //添加记录
        userPatient.setPatientStatus("BE_ACCEPTED");
        i = userPatientService.insertSelective(userPatient);
        if (i > 0) {
            statisticServer.addOrderSuccess(userId);
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
            return turnPatientExistResponse("该患者不可以转给该医生");
        }

        userPatient.setPatientSource("转诊");
        userPatient.setPatientStatus("TO_BE_ACCEPTED");
        i = userPatientService.insertSelective(userPatient);

        if (i > 0) {

            UserPatient oldUserPatient = new UserPatient();
            oldUserPatient.setUserId(getToken());
            oldUserPatient.setPatientId(patientId);
            userPatientService.deleteByParam(oldUserPatient);

            return insertSuccseeResponse("转诊成功");
        }
        return insertFailedResponse("转诊失败");
    }
}



