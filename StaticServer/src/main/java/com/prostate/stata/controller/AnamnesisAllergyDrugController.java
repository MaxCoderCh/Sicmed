package com.prostate.stata.controller;

import com.prostate.stata.beans.ChNameBean;
import com.prostate.stata.entity.AnamnesisAllergyDrug;
import com.prostate.stata.service.AnamnesisAllergyDrugService;
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
@RequestMapping(value = "anamnesisAllergyDrug")
public class AnamnesisAllergyDrugController extends BaseController {


    @Autowired
    private AnamnesisAllergyDrugService anamnesisAllergyDrugService;

    /**
     * 模糊查询过敏药物
     *
     * @return
     */
    @PostMapping(value = "search")
    public Map search(String spellName) {
        log.info("#########m模糊查询过敏药物########");
        //参数校验
        if (spellName == null || "".equals(spellName)) {
            return emptyParamResponse();
        }
        //创建查询条件
        AnamnesisAllergyDrug anamnesisAllergyDrug = new AnamnesisAllergyDrug();
        anamnesisAllergyDrug.setSpellName(spellName);

        //查询数据
        List<AnamnesisAllergyDrug> anamnesisAllergyDrugList = anamnesisAllergyDrugService.selectByParams(anamnesisAllergyDrug);

        //查询结果不为空时请求响应
        if (anamnesisAllergyDrugList != null && anamnesisAllergyDrugList.size() > 0) {

            return querySuccessResponse(anamnesisAllergyDrugList);
        }
        //查询结果为空时请求响应
        return queryEmptyResponse();

    }

    /**
     * 单个添加过敏药物
     *
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
        AnamnesisAllergyDrug anamnesisAllergyDrug = new AnamnesisAllergyDrug();
        //常用药品对象赋值
        anamnesisAllergyDrug.setAllergyDrugName(drugName);
        anamnesisAllergyDrug.setSpellName(SpellUtils.getPinYinHeaderChar(drugName));
        anamnesisAllergyDrug.setAllergyDrugNumber("0");
        //常用药品对象添加
        int i = anamnesisAllergyDrugService.insertSelective(anamnesisAllergyDrug);
        //药品添加结果校验
        if (i >= 0) {
            return insertSuccseeResponse();
        }
        return insertFailedResponse();
    }

    /**
     * 批量添加过敏药物
     *
     * @param drugNames
     * @return
     */
    @PostMapping(value = "insertAllergyDrugs")
    public Map insertAllergyDrugs(String drugNames) {

        resultMap = new LinkedHashMap<>();
        if (drugNames == null || "".equals(drugNames)) {
            return emptyParamResponse();
        }
        String[] strs = drugNames.replaceAll("\\s*", "").split("#");
        for (String s : strs) {
            //创建插入对象
            AnamnesisAllergyDrug anamnesisAllergyDrug = new AnamnesisAllergyDrug();
            anamnesisAllergyDrug.setAllergyDrugName(s);
            anamnesisAllergyDrug.setSpellName(SpellUtils.getPinYinHeaderChar(s).replace("(", "").replace(")", ""));
            anamnesisAllergyDrug.setAllergyDrugNumber("0");

            anamnesisAllergyDrugService.insertSelective(anamnesisAllergyDrug);
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

        AnamnesisAllergyDrug anamnesisAllergyDrug = anamnesisAllergyDrugService.selectById(id);

        return querySuccessResponse(anamnesisAllergyDrug.getAllergyDrugName());
    }
}
