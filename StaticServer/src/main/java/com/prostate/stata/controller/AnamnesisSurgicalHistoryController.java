package com.prostate.stata.controller;

import com.prostate.stata.entity.AnamnesisIllness;
import com.prostate.stata.entity.AnamnesisSurgicalHistory;
import com.prostate.stata.service.AnamnesisSurgicalHistoryService;
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
@RequestMapping(value = "surgicalHistory")
public class AnamnesisSurgicalHistoryController extends BaseController{

    @Autowired
    private AnamnesisSurgicalHistoryService anamnesisSurgicalHistoryService;

    @PostMapping(value = "search")
    public Map search(String spellName) {
        log.info("#########模糊查询手术史类疾病########");
        //参数校验
        if (spellName == null || "".equals(spellName)) {
            return emptyParamResponse();
        }
        //创建查询条件
        AnamnesisSurgicalHistory anamnesisSurgicalHistory = new AnamnesisSurgicalHistory();
        anamnesisSurgicalHistory.setSurgicalHistorySpellName(spellName);
        //查询数据
        List<AnamnesisSurgicalHistory> anamnesisSurgicalHistoryList = anamnesisSurgicalHistoryService.selectByParams(anamnesisSurgicalHistory);

        //查询结果不为空时请求响应
        if (anamnesisSurgicalHistoryList != null && anamnesisSurgicalHistoryList.size() > 0) {
            return querySuccessResponse(anamnesisSurgicalHistoryList);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();

    }

    /**
     * 单个添加病史类疾病
     */
    @PostMapping(value = "insert")
    public Map insert(String surgicalHistoryName) {
        log.info("#########添加一个手术史类疾病########");

        if (surgicalHistoryName == null || "".equals(surgicalHistoryName)) {
            return emptyParamResponse();
        }

        AnamnesisSurgicalHistory anamnesisSurgicalHistory = new AnamnesisSurgicalHistory();
        surgicalHistoryName = surgicalHistoryName.replaceAll("\\s*", "");
        anamnesisSurgicalHistory.setSurgicalHistoryName(surgicalHistoryName);
        anamnesisSurgicalHistory.setSurgicalHistorySpellName(SpellUtils.getPinYinHeaderChar(surgicalHistoryName));

        int i = anamnesisSurgicalHistoryService.insertSelective(anamnesisSurgicalHistory);
        if (i >= 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 批量添加病史类疾病
     */
    @PostMapping(value = "insertSurgicalHistorys")
    public Map insertIllnesses(String surgicalHistoryNames) {
        log.info("#########批量添加病史类疾病########");

        if (surgicalHistoryNames == null || "".equals(surgicalHistoryNames)) {
            return emptyParamResponse();
        }

        String[] strings = surgicalHistoryNames.replaceAll("\\s*", "").split("#");
        for (String string : strings) {
            AnamnesisSurgicalHistory anamnesisSurgicalHistory = new AnamnesisSurgicalHistory();

            anamnesisSurgicalHistory.setSurgicalHistoryName(string);
            anamnesisSurgicalHistory.setSurgicalHistorySpellName(SpellUtils.getPinYinHeaderChar(SpellUtils.getPinYinHeaderChar(string.replace("(", "").replace(")", ""))));
            anamnesisSurgicalHistoryService.insertSelective(anamnesisSurgicalHistory);
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

        AnamnesisSurgicalHistory anamnesisSurgicalHistory = anamnesisSurgicalHistoryService.selectById(id);

        return querySuccessResponse(anamnesisSurgicalHistory.getSurgicalHistoryName());
    }

}
