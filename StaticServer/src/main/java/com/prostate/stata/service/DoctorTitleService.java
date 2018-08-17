package com.prostate.stata.service;

import com.prostate.stata.entity.DoctorTitle;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface DoctorTitleService extends BaseService<DoctorTitle> {

    Map<String,String> getDoctorTitleJson();
}
