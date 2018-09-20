package com.prostate.record.service;

import java.util.Map;

public interface FeignService {

    Map<String, String> OrderServerGetOrder(String orderId) throws Exception;

    String StaticServerGetAnamnesisAllergyDrugById(String id) throws Exception;

    String StaticServerGetAnamnesisEatingDrugById(String id) throws Exception;

    String StaticServerGetAnamnesisIllnessById(String id) throws Exception;

    String StaticServerGetSurgicalHistoryById(String id) throws Exception;

    Map<String,String> StaticServerGetCityDetail(String cityId) throws Exception;

    String StaticServerGetEducationById(String id) throws Exception;

    String StaticServerGetBloodGroupById(String id) throws Exception;

    String StaticServerGetNationById(String id) throws Exception;

    String StaticServerGetProfessionById(String id) throws Exception;

    Map<String, String> StatisticServerAddOrderSuccess(String userId) throws Exception;

    Map<String, String> ThirdServeridCard(String url) throws Exception;

}
