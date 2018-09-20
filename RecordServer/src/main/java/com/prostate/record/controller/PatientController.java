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
 * 患者用户 controller
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

    /**
     * WeChat 根据身份证 添加 患者
     *
     * @param idCardUrl
     * @return
     */
    @PostMapping(value = "add")
    public Map add(String idCardUrl) {
        if (StringUtils.isBlank(idCardUrl)) {
            return emptyParamResponse();
        }
        Map<String, String> idCardInfo = null;
        try {
            idCardInfo = feignService.ThirdServeridCard(idCardUrl);
        } catch (Exception e) {
            log.error("根据身份证:" + idCardUrl + " 获取 身份证信息 失败");
            insertFailedResponse();
        }
        String idCard = String.valueOf(idCardInfo.get("id"));

        Patient patient = patientService.selectByIdCard(idCard);
        if (patient != null) {
            return insertSuccseeResponse(patient);
        }
        patient = new Patient();
        patient.setPatientCard(idCard);
        patient.setPatientName(String.valueOf(idCardInfo.get("name")));
        patient.setPatientSex(String.valueOf(idCardInfo.get("sex")));
        patient.setDetailAddress(String.valueOf(idCardInfo.get("address")));

        patient.setCreateDoctor(getToken());
        patient.setPatientNumber("PRA" + System.currentTimeMillis());
        patient.setPatientAge(IdCardUtil.getAgeByIdCard(patient.getPatientCard()));

        int i = patientService.insertSelective(patient);
        if(i>0){
            return insertSuccseeResponse(patient);
        }
        return insertFailedResponse();
    }

    /**
     * 添加患者 信息
     * @param patient
     * @return
     */
    @PostMapping(value = "addPatient")
    public Object addPatient(@Valid Patient patient) {
        resultMap = new LinkedHashMap<>();
        if (patient.getPatientName() == null || "".equals(patient.getPatientName())) {

            return emptyParamResponse();
        }
        patient.setCreateDoctor(getToken());
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
     * @return
     */
    @PostMapping(value = "getPatientList")
    public Map getPatientList(String patientName, String number) {
        log.info("#########医生端查询患者列表########");

        //创建查询条件
        PatientBean patientBean = new PatientBean();
        patientBean.setCreateDoctor(getToken());

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
    public Map getPatientDetailByToken() {
        PatientBean patientBean = patientService.selectPatientDetailById(getToken());
        if (patientBean != null) {
            return querySuccessResponse(patientBean);
        }
        return queryEmptyResponse();
    }


    /**
     * WeChat 修改患者信息
     * @param patient
     * @return
     */
    @PostMapping(value = "update")
    public Map updatePatient(Patient patient) {
        //参数校验
        if (StringUtils.isBlank(patient.getId())) {
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
        queryPatientParamBean.setPatientStatus("BE_ACCEPTED");
        List<PatientListBean> patientListBeanList = patientService.queryByParams(queryPatientParamBean);
        if (patientListBeanList != null && patientListBeanList.size() > 0) {
            return querySuccessResponse(patientListBeanList);
        }
        return queryEmptyResponse();

    }


}































