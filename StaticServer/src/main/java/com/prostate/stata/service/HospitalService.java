package com.prostate.stata.service;

import com.prostate.stata.entity.Hospital;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface HospitalService extends BaseService<Hospital> {

    Map<String,String> getHospitalJson();
}
