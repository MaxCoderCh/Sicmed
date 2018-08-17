package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.DiseaseRecord;
import org.springframework.stereotype.Service;

@Service
public interface DiseaseRecordService extends BaseService<DiseaseRecord>{
    int deleteByImgPath(String imgPath);
}
