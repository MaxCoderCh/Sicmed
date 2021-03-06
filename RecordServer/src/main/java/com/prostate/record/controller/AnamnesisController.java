package com.prostate.record.controller;

import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.ParamEntiey;
import com.prostate.record.service.AnamnesisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "anamnesis")
public class AnamnesisController extends BaseController {

    @Autowired
    private AnamnesisService anamnesisService;


    /**
     * 单独添加病史信息
     *
     * @param paramEntiey
     * @return
     */
    @PostMapping(value = "addAnamnesis")
    public Map addAnamnesis( @Valid ParamEntiey paramEntiey) {
        log.info("============患者档案既往病史添加========");
        resultMap = new LinkedHashMap<>();
        //参数校验
        if (paramEntiey == null) {
            return emptyParamResponse();
        }

        //遍历添加病史项
        String patientId = paramEntiey.getPatientId();
        if (patientId == null && "".equals(patientId)) {
            return emptyParamResponse();
        }
        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] otherIds = paramEntiey.getOtherIds();

        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                anamnesis.setCreateDoctor(getToken());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                anamnesis.setCreateDoctor(getToken());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                anamnesis.setCreateDoctor(getToken());
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (otherIds != null && otherIds.length > 0) {
            for (String otherId : otherIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setAnamnesisRemark(otherId);
                anamnesis.setAnamnesisTypeId("0045a520eb9d4a3f93fbef4a2e9de0cf");
                anamnesis.setCreateDoctor(getToken());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        return insertSuccseeResponse();
    }

    /**
     * WeChat 删除 病史标签
     * @param id
     * @return
     */
    @PostMapping(value = "delete")
    public Map deleteAnamnesis(String id, String patientId) {
        if (id == null || "".equals(id)) {
            return emptyParamResponse();
        }
        int i = anamnesisService.deleteById(id, patientId);
        if (i >= 0) {
            return deleteSuccseeResponse();
        }
        return deleteFailedResponse();
    }

    /**
     * 修改 就诊人  病历 信息
     * @param paramEntiey
     * @return
     */
    @PostMapping(value = "update")
    public Map update(ParamEntiey paramEntiey) {

        if (StringUtils.isBlank(paramEntiey.getPatientId())) {
            return emptyParamResponse();
        }

        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] anamnesisSurgicalHistoryIds = paramEntiey.getAnamnesisSurgicalHistoryIds();

        /**
         * 修改病史信息
         */
        boolean alreadyExist;
        Anamnesis anamnesis;
        if (anamnesisAllergyDrugIds != null && anamnesisAllergyDrugIds.length > 0) {
            for (String anamnesisAllergyDrugId : anamnesisAllergyDrugIds) {
                anamnesis = new Anamnesis();
                anamnesis.setPatientId(paramEntiey.getPatientId());
                anamnesis.setOrderId(anamnesisAllergyDrugId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ebe7926a7c7");
                alreadyExist = anamnesisService.checkRepeated(anamnesis);
                if (alreadyExist) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);
            }
        }
        if (anamnesisEatingDrugIds != null && anamnesisEatingDrugIds.length > 0) {
            for (String anamnesisEatingDrugId : anamnesisEatingDrugIds) {
                anamnesis = new Anamnesis();
                anamnesis.setPatientId(paramEntiey.getPatientId());
                anamnesis.setOrderId(anamnesisEatingDrugId);
                anamnesis.setAnamnesisTypeId("00163e4597b14fe787c86e22b7946790");
                alreadyExist = anamnesisService.checkRepeated(anamnesis);
                if (alreadyExist) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisIllnessIds != null && anamnesisIllnessIds.length > 0) {
            for (String anamnesisIllnessId : anamnesisIllnessIds) {
                anamnesis = new Anamnesis();
                anamnesis.setPatientId(paramEntiey.getPatientId());
                anamnesis.setOrderId(anamnesisIllnessId);
                anamnesis.setAnamnesisTypeId("00106a226f04411b885e3f328acba4d7");
                alreadyExist = anamnesisService.checkRepeated(anamnesis);
                if (alreadyExist) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);

            }
        }
        if (anamnesisSurgicalHistoryIds != null && anamnesisSurgicalHistoryIds.length > 0) {
            for (String anamnesisSurgicalHistoryId : anamnesisSurgicalHistoryIds) {
                anamnesis = new Anamnesis();
                anamnesis.setPatientId(paramEntiey.getPatientId());
                anamnesis.setOrderId(anamnesisSurgicalHistoryId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ede7926a7c7");
                alreadyExist = anamnesisService.checkRepeated(anamnesis);
                if (alreadyExist) {
                    continue;
                }
                anamnesisService.insertSelective(anamnesis);
            }
        }
        return insertSuccseeResponse(paramEntiey.getPatientId());

    }

}
