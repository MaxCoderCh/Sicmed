package com.prostate.stata.controller;


import com.prostate.stata.entity.AnamnesisEatingDrug;
import com.prostate.stata.entity.AnamnesisIllness;
import com.prostate.stata.service.AnamnesisIllnessService;
import com.prostate.stata.util.SpellUtils;
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
@RequestMapping(value = "anamnesisIllness")
public class AnamnesisIllnessController extends BaseController {

    private final AnamnesisIllnessService anamnesisIllnessService;

    @Autowired
    public AnamnesisIllnessController(AnamnesisIllnessService anamnesisIllnessService) {
        this.anamnesisIllnessService = anamnesisIllnessService;
    }


    @PostMapping(value = "search")
    public Map search(String spellName) {
        log.info("#########模糊查询病史类疾病########");
        //参数校验
        if (spellName == null || "".equals(spellName)) {
            return emptyParamResponse();
        }
        //创建查询条件
        AnamnesisIllness anamnesisIllness = new AnamnesisIllness();
        anamnesisIllness.setSpellName(spellName);
        //查询数据
        List<AnamnesisIllness> anamnesisIllnessList = anamnesisIllnessService.selectByParams(anamnesisIllness);

        //查询结果不为空时请求响应
        if (anamnesisIllnessList != null && anamnesisIllnessList.size() > 0) {
            return querySuccessResponse(anamnesisIllnessList);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();

    }

    /**
     * 单个添加病史类疾病
     */
    @PostMapping(value = "insert")
    public Map insert(String illnessName) {
        log.info("#########添加一个病史类疾病########");

        if (illnessName == null || "".equals(illnessName)) {
            return emptyParamResponse();
        }

        AnamnesisIllness anamnesisIllness = new AnamnesisIllness();
        illnessName = illnessName.replaceAll("\\s*", "");
        anamnesisIllness.setAnamnesisIllnessName(illnessName);
        anamnesisIllness.setSpellName(SpellUtils.getPinYinHeaderChar(illnessName));

        int i = anamnesisIllnessService.insertSelective(anamnesisIllness);
        if (i >= 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 批量添加病史类疾病
     */
    @PostMapping(value = "insertIllnesses")
    public Map insertIllnesses(String illnessNames) {
        log.info("#########批量添加病史类疾病########");

        if (illnessNames == null || "".equals(illnessNames)) {
            return emptyParamResponse();
        }

        String[] strings = illnessNames.replaceAll("\\s*", "").split("#");
        for (String string : strings) {
            AnamnesisIllness anamnesisIllness = new AnamnesisIllness();
            anamnesisIllness.setAnamnesisIllnessName(string);
            anamnesisIllness.setSpellName(SpellUtils.getPinYinHeaderChar(SpellUtils.getPinYinHeaderChar(string.replace("(", "").replace(")", ""))));

            anamnesisIllnessService.insertSelective(anamnesisIllness);

        }
        return insertSuccseeResponse();
    }


    /**
     * 根据Id查询 名称
     *
     * @param id
     * @return
     */
    @GetMapping(value = "getById")
    public Map getById(String id){

        AnamnesisIllness anamnesisIllness = anamnesisIllnessService.selectById(id);

        return querySuccessResponse(anamnesisIllness.getAnamnesisIllnessName());
    }
}
