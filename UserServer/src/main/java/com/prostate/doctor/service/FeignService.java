package com.prostate.doctor.service;

import java.util.Map;

public interface FeignService {

    Map<String, String> ThirdServeridCard(String url) throws Exception;

    void ThirdServerSendRegisterCode(String phoneNumber) throws Exception;

    void ThirdServerSendLoginCode(String phoneNumber) throws Exception;

    void ThirdServerSendPasswordReplaceCode(String phoneNumber) throws Exception;

    void StatisticServerAddOrderSuccess(String userId) throws Exception;

    void StatisticServerAddDoctorFocus(String userId) throws Exception;

    void StatisticServerAddPatientFocus(String userId) throws Exception;

    void StatisticServerAddDoctorClick(String userId) throws Exception;

    void StatisticServerAddPatientClick(String userId) throws Exception;

    void StatisticServerAddDoctorUnFocus(String userId) throws Exception;

    void StatisticServerAddPatientUnFocus(String userId) throws Exception;

    Map<String, String> StatisticServerGetDoctorCount(String userId) throws Exception;

    String StaticServerGetHospitalById(String id) throws Exception;

    String StaticServerGetBranchById(String id) throws Exception;

    String StaticServerGetTitleById(String id) throws Exception;

    Map<String, String> StaticServerHospitalJson() throws Exception;

    Map<String, String> StaticServerBranchServiceJson() throws Exception;

    Map<String, String> StaticServerDoctorTitleJson() throws Exception;
}
