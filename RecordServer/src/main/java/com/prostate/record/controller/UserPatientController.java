package com.prostate.record.controller;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;
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





}



