package com.sicmed.assessmen.controller;

import com.sicmed.assessmen.beans.NihCpsiScoreBean;
import com.sicmed.assessmen.entity.PatientNihCpsiScore;
import com.sicmed.assessmen.service.PatientNihCpsiScoreService;
import com.sicmed.assessmen.util.DateUtils;
import com.sicmed.assessmen.util.NihCpsiScoreUtils;
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
@RequestMapping(value = "patientNihCpsiScore")
public class PatientNihCpsiScoreController extends BaseController {

    @Autowired
    private PatientNihCpsiScoreService patientNihCpsiScoreService;


    /**
     * WeChat 慢性前列腺炎症状评分添加
     *
     * @param patientNihNpsiScore
     * @return
     */
    @RequestMapping(value = "add")
    public Map add(PatientNihCpsiScore patientNihNpsiScore) {
        //参数校验
        if (patientNihNpsiScore == null) {
            return emptyParamResponse();
        }
        patientNihNpsiScore.setCreateDoctor(getToken());

        List<Integer> scoreList = NihCpsiScoreUtils.getScores(patientNihNpsiScore.getAnswer());
        String caution = NihCpsiScoreUtils.checkDegree(scoreList);
        patientNihNpsiScore.setCaution(caution);
        String optionScore = NihCpsiScoreUtils.getOptionScore(scoreList);
        patientNihNpsiScore.setOptionScore(optionScore);

        Date cds =  DateUtils.stringToDate(DateUtils.getCurrentDate());
        patientNihNpsiScore.setCreateTime(cds);

        log.info("cds="+cds.toGMTString());
        PatientNihCpsiScore checkPatientNihNpsiScore = patientNihCpsiScoreService.selectByCreateTimeAndPatientId(patientNihNpsiScore);

        if (checkPatientNihNpsiScore != null) {
            return insertRepeatResponse("今日已添加过前列腺症状评分");
        }

        patientNihCpsiScoreService.insertSelective(patientNihNpsiScore);

        return insertSuccseeResponse(patientNihNpsiScore);
    }

    /**
     * 慢性前列腺炎症状评分修改
     *
     * @param patientNihNpsiScore
     * @return
     */
    @RequestMapping(value = "update")
    public Map update(PatientNihCpsiScore patientNihNpsiScore) {
        //参数校验
        if (patientNihNpsiScore == null || patientNihNpsiScore.getId() == null) {
            return emptyParamResponse();
        }

        patientNihNpsiScore.setUpdateDoctor(getToken());

        List<Integer> scoreList = NihCpsiScoreUtils.getScores(patientNihNpsiScore.getAnswer());
        String caution = NihCpsiScoreUtils.checkDegree(scoreList);
        patientNihNpsiScore.setCaution(caution);
        String optionScore = NihCpsiScoreUtils.getOptionScore(scoreList);
        patientNihNpsiScore.setOptionScore(optionScore);

        patientNihCpsiScoreService.updateSelective(patientNihNpsiScore);

        return updateSuccseeResponse(patientNihNpsiScore);
    }

    /**
     * WeChat 根据ID查询一条NIH-NPSI评估记录
     *
     * @param nihCpsiScoreId
     * @return
     */
    @PostMapping(value = "getById")
    public Map getById(String nihCpsiScoreId) {
        //参数校验
        if (nihCpsiScoreId == null) {

            return emptyParamResponse();
        }
        NihCpsiScoreBean nihCpsiScoreBean = patientNihCpsiScoreService.getById(nihCpsiScoreId);
        if (nihCpsiScoreBean != null) {
            nihCpsiScoreBean.setInflammationScore(NihCpsiScoreUtils.countInflammationScore(nihCpsiScoreBean.getAnswer()));
            nihCpsiScoreBean.setLifeScore(NihCpsiScoreUtils.countLifeScore(nihCpsiScoreBean.getAnswer()));
            nihCpsiScoreBean.setSymptomScore(NihCpsiScoreUtils.countSymptomScore(nihCpsiScoreBean.getAnswer()));
            return querySuccessResponse(nihCpsiScoreBean);
        }
        return queryEmptyResponse();
    }
}
