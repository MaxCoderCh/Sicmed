package com.prostate.record.controller;

import com.prostate.record.beans.PatientBean;
import com.prostate.record.beans.PatientListBean;
import com.prostate.record.beans.QueryPatientParamBean;
import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.Doctor;
import com.prostate.record.entity.Patient;
import com.prostate.record.entity.WechatUser;
import com.prostate.record.service.PatientService;
import com.prostate.record.util.IdCardUtil;
import com.prostate.record.validator.phoneValidation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 患者用户 Controller
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "patient")
public class PatientController extends BaseController {


    private PatientService patientService;



    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "add")
    public Map add(String idCardUrl) {
        if (StringUtils.isBlank(idCardUrl)) {
            return emptyParamResponse();
        }
        Map<String, Object> idCardMap = thirdServer.idCard(idCardUrl);

        //添加医生个人信息
        Patient patient = new Patient();
        log.info(idCardMap.get("result").toString());
        Map<String, Object> idCardInfo = (Map<String, Object>) idCardMap.get("result");

        patient.setPatientCard(idCardInfo.get("id").toString());
        patient.setPatientName(idCardInfo.get("name").toString());
        patient.setPatientSex(idCardInfo.get("sex").toString());
        patient.setDetailAddress(idCardInfo.get("address").toString());

        patient.setCreateDoctor(getToken());
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));

        int i = patientService.insertSelective(patient);

        return insertSuccseeResponse(patient);
    }

    /**
     * 添加患者 信息
     * @param patient
     * @param token
     * @return
     */
    @PostMapping(value = "addPatient")
    public Object addPatient(@Valid Patient patient, String token) {
        resultMap = new LinkedHashMap<>();
        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {

            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patient.setCreateDoctor(doctor.getId());
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));
        if (patient.getId() == null || "".equals(patient.getId()) || patient.getId().length() < 32) {
            int i = patientService.insertSelective(patient);
            if (i >= 0) {
                return insertSuccseeResponse();
            }
            return insertFailedResponse();
        }
        int i = patientService.updateSelective(patient);
        if (i >= 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();

    }


    /**
     * 根据ID查询患者基本信息
     *
     * @param patientId
     * @param
     * @return
     */
    @PostMapping(value = "getPatientDetailById")
    public Map getPatientDetailById(String patientId) {

        if (patientId == null || "".equals(patientId)) {
            return emptyParamResponse();
        }
        PatientBean patientBean = patientService.selectPatientDetailById(patientId);
        if (patientBean != null) {
            return querySuccessResponse(patientBean);
        }
        return queryEmptyResponse();

    }

    /**
     * 查询患者列表
     *
     * @param token
     * @return
     */
    @PostMapping(value = "getPatientList")
    public Map getPatientList(String token, String patientName, String number) {
        log.info("#########医生端查询患者列表########");

        Doctor doctor = redisSerive.getDoctor(token);
        //创建查询条件
        PatientBean patientBean = new PatientBean();
        patientBean.setCreateDoctor(doctor.getId());

        if (patientName != null && !"".equals(patientName)) {
            patientBean.setPatientName(patientName);
        }
        if (number != null && !"".equals(number)) {
            if (phoneValidation.regexCheck(number)) {
                patientBean.setPatientPhone(number);
            } else {
                patientBean.setPatientNumber(number);
            }
        }

        //查询数据
        String count = patientService.selectCountByParams(patientBean);
        List<PatientBean> patientBeanList = patientService.selectByParamss(patientBean);
        //查询结果不为空时请求响应
        if (patientBeanList != null && patientBeanList.size() > 0) {
            return querySuccessResponse(patientBeanList, count);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();
    }


    /**
     * Order Server 查询 患者列表
     */
    @PostMapping(value = "getPatientListByIds")
    public Map getPatientListByIds(String[] patientIds) {

        List<WeChatPatientBean> weChatPatientBeanList = patientService.getPatientListByIds(patientIds);

        if (weChatPatientBeanList == null || weChatPatientBeanList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(weChatPatientBeanList);
    }
/*****************************************微信端 接口 **************************************************/
    /**
     * 根据微信token查询患者基本信息
     *
     * @param
     * @param
     * @return
     */
    @PostMapping(value = "getPatientDetailByToken")
    public Map getPatientDetailByToken(String token) {
        WechatUser wechatUser = redisSerive.getWechatUser(token);
        PatientBean patientBean = patientService.selectPatientDetailById(wechatUser.getId());
        if (patientBean != null) {
            return querySuccessResponse(patientBean);
        }
        return queryEmptyResponse();
    }


    /**
     * 修改患者信息
     * @param patient
     * @param token
     * @return
     */
    @PostMapping(value = "update")
    public Object updatePatient(Patient patient, String token) {
        resultMap = new LinkedHashMap<>();
        if (patient.getId() == null || "".equals(patient.getId())) {
            return emptyParamResponse();
        }
        int i = patientService.updateSelective(patient);
        if (i >= 0) {
            return updateSuccseeResponse();
        }
        return updateFailedResponse();

    }

    /**
     * 修改患者信息
     *
     * @return
     */
    @GetMapping(value = "getBaseInfoById")
    public Map getBaseInfoById(String patientId) {
        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }
        Patient patient = patientService.selectById(patientId);
        if (patient != null) {
            return querySuccessResponse(patient);
        }
        return queryEmptyResponse();

    }

    /**
     * APP 查询 患者列表
     */
    @GetMapping(value = "findPatientList")
    public Map findPatientList(String stickerId, String patientName) {

        QueryPatientParamBean queryPatientParamBean = new QueryPatientParamBean();

        queryPatientParamBean.setDoctorId(getToken());
        queryPatientParamBean.setPatientName(patientName);
        queryPatientParamBean.setStickerId(stickerId);

        List<PatientListBean> patientListBeanList = patientService.queryByParams(queryPatientParamBean);
        if (patientListBeanList != null && patientListBeanList.size() > 0) {
            return querySuccessResponse(patientListBeanList);
        }
        return queryEmptyResponse();

    }


}































