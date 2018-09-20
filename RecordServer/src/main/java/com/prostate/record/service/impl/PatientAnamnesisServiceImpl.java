package com.prostate.record.service.impl;

import com.prostate.record.beans.PatientAnamnesisBean;
import com.prostate.record.entity.Anamnesis;
import com.prostate.record.entity.Patient;
import com.prostate.record.feignService.StaticServer;
import com.prostate.record.mapper.slaver.AnamnesisReadMapper;
import com.prostate.record.mapper.slaver.PatientReadMapper;
import com.prostate.record.service.FeignService;
import com.prostate.record.service.PatientAnamnesisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PatientAnamnesisServiceImpl implements PatientAnamnesisService {


    @Autowired
    private PatientReadMapper patientReadMapper;

    @Autowired
    private AnamnesisReadMapper anamnesisReadMapper;

    @Autowired
    protected FeignService feignService;

    /**
     * @param patientId
     * @return
     */
    @Cacheable(value = "HealthRrecord", key = "#patientId", unless = "#result == null")
    @Override
    public PatientAnamnesisBean getHealthRrecord(String patientId){

        //查询患者信息
        Patient patient = patientReadMapper.selectById(patientId);
        while (patient == null) {
            return null;
        }
        PatientAnamnesisBean patientAnamnesisBean = new PatientAnamnesisBean();
        patientAnamnesisBean.setPatient(patient);

        //查询城市信息
        String cityId = patient.getCityId();
        if (cityId != null && cityId.length() == 32) {
            Map<String,String> cityDetailMap = null;
            try {
                cityDetailMap = feignService.StaticServerGetCityDetail(patient.getCityId());
            } catch (Exception e) {
                log.error("根据CITY ID 查询患 PATIENT ID:"+ patientId +"患者 的 城市信息失败");
            }
            patientAnamnesisBean.setCityDetailMap(cityDetailMap);
        }
        //查询血型信息
        String bloodGroupId = patient.getBloodGroupId();
        if (bloodGroupId != null && bloodGroupId.length() == 32) {
            try {
                patientAnamnesisBean.setBloodGroup(feignService.StaticServerGetBloodGroupById(bloodGroupId));
            } catch (Exception e) {
                log.error("根据bloodGroup ID 查询患 PATIENT ID:"+ patientId +"患者 的 血型信息失败");
            }
        }

        //查询文化程度
        String educationId = patient.getEducationId();
        if (educationId != null && educationId.length() == 32) {
            try {
                patientAnamnesisBean.setEducationName(feignService.StaticServerGetEducationById(educationId));
            } catch (Exception e) {
                log.error("根据education ID 查询患 PATIENT ID:"+ patientId +"患者 的 文化程度失败");
            }
        }
        //查询民主信息
        String nationId = patient.getNationId();
        if (nationId != null && nationId.length() == 32) {
            try {
                patientAnamnesisBean.setNationName(feignService.StaticServerGetNationById(nationId));
            } catch (Exception e) {
                log.error("根据nation ID 查询患 PATIENT ID:"+ patientId +"患者 的 民族信息失败");
            }
        }
        //查询职业信息
        String professionId = patient.getProfessionId();
        if (professionId != null && professionId.length() == 32) {
            try {
                patientAnamnesisBean.setProfessionName(feignService.StaticServerGetProfessionById(professionId));
            } catch (Exception e) {
                log.error("根据profession ID 查询患 PATIENT ID:"+ patientId +"患者 的 职业信息失败");
            }
        }

        //查询病历信息
        List<Anamnesis> anamnesisList = anamnesisReadMapper.getByPatientId(patientId);
        while (anamnesisList == null) {
            return patientAnamnesisBean;
        }

        List<Anamnesis> anamnesisAllergyDrugList = new ArrayList<>();
        List<Anamnesis> anamnesisEatingDrugList = new ArrayList<>();
        List<Anamnesis> anamnesisIllnessList = new ArrayList<>();
        List<Anamnesis> anamnesisSurgicalHistoryList = new ArrayList<>();
        List<Anamnesis> otherList = new ArrayList<>();
        //查询 病历中 疾病 信息
        for (Anamnesis anamnesis : anamnesisList) {
            switch (anamnesis.getAnamnesisTypeId()) {
                case "0007fe67fa7c4c4195018ebe7926a7c7":
                    try {
                        anamnesis.setAnamnesisRemark(feignService.StaticServerGetAnamnesisAllergyDrugById(anamnesis.getOrderId()));
                    } catch (Exception e) {
                        log.error("根据other ID 查询患 PATIENT ID:"+ patientId +"患者 的 病史信息失败");
                    }
                    anamnesisAllergyDrugList.add(anamnesis);
                    break;
                case "00163e4597b14fe787c86e22b7946790":
                    try {
                        anamnesis.setAnamnesisRemark(feignService.StaticServerGetAnamnesisEatingDrugById(anamnesis.getOrderId()));
                    } catch (Exception e) {
                        log.error("根据other ID 查询患 PATIENT ID:"+ patientId +"患者 的 病史信息失败");
                    }
                    anamnesisEatingDrugList.add(anamnesis);
                    break;
                case "00106a226f04411b885e3f328acba4d7":
                    try {
                        anamnesis.setAnamnesisRemark(feignService.StaticServerGetAnamnesisIllnessById(anamnesis.getOrderId()));
                    } catch (Exception e) {
                        log.error("根据other ID 查询患 PATIENT ID:"+ patientId +"患者 的 病史信息失败");
                    }
                    anamnesisIllnessList.add(anamnesis);
                    break;
                case "0007fe67fa7c4c4195018ede7926a7c7":
                    try {
                        anamnesis.setAnamnesisRemark(feignService.StaticServerGetSurgicalHistoryById(anamnesis.getOrderId()));
                    } catch (Exception e) {
                        log.error("根据other ID 查询患 PATIENT ID:"+ patientId +"患者 的 病史信息失败");
                    }
                    anamnesisSurgicalHistoryList.add(anamnesis);
                    break;
                case "0045a520eb9d4a3f93fbef4a2e9de0cf":
                    otherList.add(anamnesis);
                    break;
                default:
                    break;
            }
        }
        patientAnamnesisBean.setAnamnesisAllergyDrugList(anamnesisAllergyDrugList);
        patientAnamnesisBean.setAnamnesisEatingDrugList(anamnesisEatingDrugList);
        patientAnamnesisBean.setAnamnesisIllnessList(anamnesisIllnessList);
        patientAnamnesisBean.setAnamnesisSurgicalHistoryList(anamnesisSurgicalHistoryList);
        patientAnamnesisBean.setOtherList(otherList);

        return patientAnamnesisBean;
    }
}
