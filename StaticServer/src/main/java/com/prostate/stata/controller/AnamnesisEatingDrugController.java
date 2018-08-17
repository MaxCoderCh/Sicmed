package com.prostate.stata.controller;


import com.prostate.stata.entity.AnamnesisAllergyDrug;
import com.prostate.stata.entity.AnamnesisEatingDrug;
import com.prostate.stata.service.AnamnesisEatingDrugService;
import com.prostate.stata.util.SpellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "anamnesisEatingDrug")
public class AnamnesisEatingDrugController extends BaseController {

    @Autowired
    private AnamnesisEatingDrugService anamnesisEatingDrugService;


    /**
     * 模糊查询正在服用的药物
     *
     * @return
     */
    @PostMapping(value = "search")
    public Map search(String spellName) {
        log.info("#########模糊查询正在服用的药物########");
        //参数校验
        if (spellName == null || "".equals(spellName)) {
            return emptyParamResponse();
        }
        //创建查询条件
        AnamnesisEatingDrug anamnesisEatingDrug = new AnamnesisEatingDrug();
        anamnesisEatingDrug.setSpellName(spellName);

        //查询数据
        List<AnamnesisEatingDrug> anamnesisEatingDrugList = anamnesisEatingDrugService.selectByParams(anamnesisEatingDrug);

        //查询结果不为空时请求响应
        if (anamnesisEatingDrugList != null && anamnesisEatingDrugList.size() > 0) {

            return querySuccessResponse(anamnesisEatingDrugList);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();

    }


    /**
     * 单个添加常用药物
     * @param drugName
     * @return
     */
    @PostMapping(value = "insert")
    public Map insert(String drugName) {
        //参数校验
        if (drugName == null || "".equals(drugName)) {
            //参数不合法返回信息
            return emptyParamResponse();

        }
        //创建常用药品对象
        AnamnesisEatingDrug anamnesisEatingDrug = new AnamnesisEatingDrug();
        //常用药品对象赋值
        anamnesisEatingDrug.setEatingDrugName(drugName);
        anamnesisEatingDrug.setSpellName(SpellUtils.getPinYinHeaderChar(drugName));
        anamnesisEatingDrug.setEatingDrugNumber("0");
        //常用药品对象添加
       int i = anamnesisEatingDrugService.insertSelective(anamnesisEatingDrug);
       //药品添加结果校验
       if(i>=0){
           return insertSuccseeResponse();
       }
        return insertFailedResponse();
    }

    /**
     * 批量添加常用药物
     * @param drugNames
     * @return
     */
    @PostMapping(value = "insertStrs")
    public Map insertStrs(String drugNames) {

        resultMap = new LinkedHashMap<>();
        if (drugNames == null || "".equals(drugNames)) {
           return emptyParamResponse();
        }
        String[] strs = drugNames.replaceAll("\\s*", "").split("#");
        for (String s : strs) {
            //创建插入对象
            AnamnesisEatingDrug anamnesisEatingDrug = new AnamnesisEatingDrug();
            anamnesisEatingDrug.setEatingDrugName(s);
            anamnesisEatingDrug.setSpellName(SpellUtils.getPinYinHeaderChar(s).replace("(", "").replace(")", ""));
            anamnesisEatingDrug.setEatingDrugNumber("0");

            anamnesisEatingDrugService.insertSelective(anamnesisEatingDrug);
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

        AnamnesisEatingDrug anamnesisEatingDrug = anamnesisEatingDrugService.selectById(id);

        return querySuccessResponse(anamnesisEatingDrug.getEatingDrugName());
    }
}
