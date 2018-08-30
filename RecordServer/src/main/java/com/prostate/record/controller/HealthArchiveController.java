package com.prostate.record.controller;


import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.ParamEntiey;
import com.prostate.record.entity.Patient;
import com.prostate.record.feignService.ThirdServer;
import com.prostate.record.service.AnamnesisService;
import com.prostate.record.service.PatientService;
import com.prostate.record.service.UserPatientService;
import com.prostate.record.util.IdCardUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "healthArchive")
public class HealthArchiveController extends BaseController {


    private final AnamnesisService anamnesisService;

    private final UserPatientService userPatientService;

    @Autowired
    public HealthArchiveController(AnamnesisService anamnesisService, UserPatientService userPatientService) {
        this.anamnesisService = anamnesisService;
        this.userPatientService = userPatientService;
    }

    /**
     * 同时  创建 患者 和 患者病历
     *
     * @return
     */
    @PostMapping(value = "add")
    public Map addPatient(ParamEntiey paramEntiey, String patientSource,String patientId) {

        if (StringUtils.isBlank(patientId)) {
            return emptyParamResponse();
        }

        String[] anamnesisAllergyDrugIds = paramEntiey.getAnamnesisAllergyDrugIds();
        String[] anamnesisEatingDrugIds = paramEntiey.getAnamnesisEatingDrugIds();
        String[] anamnesisIllnessIds = paramEntiey.getAnamnesisIllnessIds();
        String[] anamnesisSurgicalHistoryIds = paramEntiey.getAnamnesisSurgicalHistoryIds();
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
        if (anamnesisSurgicalHistoryIds != null && anamnesisSurgicalHistoryIds.length > 0) {
            for (String anamnesisSurgicalHistoryId : anamnesisSurgicalHistoryIds) {
                Anamnesis anamnesis = new Anamnesis();
                anamnesis.setPatientId(patientId);
                anamnesis.setOrderId(anamnesisSurgicalHistoryId);
                anamnesis.setAnamnesisTypeId("0007fe67fa7c4c4195018ede7926a7c7");
                anamnesis.setCreateDoctor(getToken());
                anamnesisService.insertSelective(anamnesis);
            }
        }
        userPatientService.addUserPatient(patientId, getToken(), patientSource);
        return insertSuccseeResponse(patientId);

    }
}
