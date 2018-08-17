package com.prostate.assessmen.controller;

import com.prostate.assessmen.beans.IpssScoreBean;
import com.prostate.assessmen.cache.redis.RedisSerive;
import com.prostate.assessmen.entity.Doctor;
import com.prostate.assessmen.entity.PatientIpssScore;
import com.prostate.assessmen.service.PatientIpssScoreService;
import com.prostate.assessmen.util.DateUtils;
import com.prostate.assessmen.util.IpssScoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "patientIpssScore")
public class PatientIpssScoreController extends BaseController {

    @Autowired
    private PatientIpssScoreService patientIpssScoreService;

    @Autowired
    private RedisSerive redisSerive;

    /**
     * 添加IPSS评估结果
     *
     * @param patientIpssScore
     * @param token
     * @return
     */
    @PostMapping(value = "add")
    public Map add(PatientIpssScore patientIpssScore, String token) {
        log.info("#########前列腺症状评分（IPSS）结果添加############生活质量指数评分（QOL)结果添加############");
        //参数校验
        if (patientIpssScore == null) {
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patientIpssScore.setCreateDoctor(doctor.getId());

        List<Integer> scoreList = IpssScoreUtils.getScores(patientIpssScore.getAnswer());
        String caution = IpssScoreUtils.checkDegree(scoreList);
        patientIpssScore.setCaution(caution);
        String optionScore = IpssScoreUtils.getOptionScore(scoreList);
        patientIpssScore.setOptionScore(optionScore);

        Date cds =  DateUtils.stringToDate(DateUtils.getCurrentDate());
        patientIpssScore.setCreateTime(cds);
        //校验单日评估次数
        PatientIpssScore checkPatientIpssScore = patientIpssScoreService.selectByCreateTimeAndPatientId(patientIpssScore);
        if (checkPatientIpssScore != null) {
            return insertRepeatResponse("今日已添加过前列腺症状评分");
        }

        patientIpssScoreService.insertSelective(patientIpssScore);

        return insertSuccseeResponse(patientIpssScore);
    }

    /**
     * 修改IPSS评估结果
     *
     * @param patientIpssScore
     * @param token
     * @return
     */
    @PostMapping(value = "update")
    public Map update(PatientIpssScore patientIpssScore, String token) {
        log.info("#########前列腺症状评分（IPSS）结果修改############生活质量指数评分（QOL)结果修改############");
        //参数校验
        if (patientIpssScore == null) {
            return emptyParamResponse();
        }
        Doctor doctor = redisSerive.getDoctor(token);
        patientIpssScore.setUpdateDoctor(doctor.getId());

        List<Integer> scoreList = IpssScoreUtils.getScores(patientIpssScore.getAnswer());
        String caution = IpssScoreUtils.checkDegree(scoreList);
        patientIpssScore.setCaution(caution);
        String optionScore = IpssScoreUtils.getOptionScore(scoreList);
        patientIpssScore.setOptionScore(optionScore);

        patientIpssScoreService.updateSelective(patientIpssScore);

        return updateSuccseeResponse(patientIpssScore);
    }

    /**
     * 根据ID查询一条IPSS评估记录
     *
     * @param ipssScoreId
     * @return
     */
    @PostMapping(value = "getById")
    public Map getById(String ipssScoreId) {
        log.info("########查询一条前列腺症状评分（IPSS）结果添加############生活质量指数评分（QOL)结果############");
        //参数校验
        if (ipssScoreId == null) {

            return emptyParamResponse();
        }
        IpssScoreBean ipssScoreBean = patientIpssScoreService.getById(ipssScoreId);

        if (ipssScoreBean != null) {
            ipssScoreBean.setSymptomScore(IpssScoreUtils.countSymptomScore(ipssScoreBean.getAnswer()));
            ipssScoreBean.setLifeScore(IpssScoreUtils.countLifeScore(ipssScoreBean.getAnswer()));
            return querySuccessResponse(ipssScoreBean);
        }
        return queryEmptyResponse();
    }
}
