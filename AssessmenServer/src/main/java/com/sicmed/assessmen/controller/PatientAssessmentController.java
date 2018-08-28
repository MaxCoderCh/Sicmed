package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.cache.redis.RedisSerive;
import com.sicmed.assessmen.entity.PatientAssessment;
import com.sicmed.assessmen.entity.WechatUser;
import com.sicmed.assessmen.service.PatientAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "assessment")
public class PatientAssessmentController extends BaseController {

    @Autowired
    private PatientAssessmentService assessmentService;

    @Autowired
    private RedisSerive redisSerive;
    /**
     * 根据患者ID查询 所有评估结果列表
     * @param patientId
     * @return
     */
    @PostMapping(value = "findByPatientId")
    public Map getPatientAssessmentList(String patientId){
        log.info("############根据患者查询 所有评估结果列表############");
        //参数校验
        if(patientId==null||"".equals(patientId)){
            return emptyParamResponse();
        }

        PatientAssessment patientAssessment =new PatientAssessment();
        patientAssessment.setId(patientId);

        List<PatientAssessment> patientAssessmentList =  assessmentService.selectByPatientId(patientAssessment);
        if(patientAssessmentList!=null&&patientAssessmentList.size()>0){
            return querySuccessResponse(patientAssessmentList);
        }
        return queryEmptyResponse();
    }

    @PostMapping(value = "getById")
    public Map getPatientAssessment(String id){
         //参数校验
         if(id==null||"".equals(id)){
             return emptyParamResponse();
         }
         PatientAssessment patientAssessment =new PatientAssessment();
         patientAssessment.setId(id);
         patientAssessment =  assessmentService.selectById(patientAssessment);
         if(patientAssessment!=null){
             return querySuccessResponse(patientAssessment);

         }
         return queryEmptyResponse();
    }

    /**
     * WeChat 根据 token 查询 所有评估结果列表
     * @return
     */
    @GetMapping(value = "findByWechatToken")
    public Map findByWechatToken(){

        PatientAssessment patientAssessment =new PatientAssessment();
        patientAssessment.setId(getToken());

        List<PatientAssessment> patientAssessmentList =  assessmentService.selectByPatientId(patientAssessment);
        if(patientAssessmentList!=null&&patientAssessmentList.size()>0){
            return querySuccessResponse(patientAssessmentList);
        }
        return queryEmptyResponse();
    }


    /**
     * WeChat 查询最近的 一条评估记录
     * @param patientId
     * @return
     */
    @PostMapping(value = "getLastByPatientId")
    public Map getLastByPatientId(String patientId){
        //参数校验
        if(patientId==null||"".equals(patientId)){
            return emptyParamResponse();
        }

        PatientAssessment patientAssessment =new PatientAssessment();
        patientAssessment.setId(patientId);

        patientAssessment = assessmentService.selectLastByPatientId(patientAssessment);
        if(patientAssessment!=null){
            return querySuccessResponse(patientAssessment);
        }
        return queryEmptyResponse();
    }


    /**
     * APP 根据患者ID查询 所有评估结果列表
     */
    @GetMapping(value = "getAssessmentListByPatient")
    public Map getAssessmentListByPatient(String patientId){
        //参数校验
        if(patientId==null||"".equals(patientId)){
            return emptyParamResponse();
        }

        PatientAssessment patientAssessment =new PatientAssessment();
        patientAssessment.setId(patientId);

        List<PatientAssessment> patientAssessmentList =  assessmentService.queryPageByParams(patientAssessment);
        if(patientAssessmentList!=null&&patientAssessmentList.size()>0){
            return querySuccessResponse(patientAssessmentList);
        }
        return queryEmptyResponse();
    }
}
