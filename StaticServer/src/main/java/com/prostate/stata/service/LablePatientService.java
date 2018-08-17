package com.prostate.stata.service;

import com.prostate.stata.entity.LablePatient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LablePatientService extends BaseService<LablePatient> {
    List<LablePatient> selectByToken(LablePatient lablePatient);

    Map<String,String> getDoctorTitleJson(LablePatient lablePatient);
}
