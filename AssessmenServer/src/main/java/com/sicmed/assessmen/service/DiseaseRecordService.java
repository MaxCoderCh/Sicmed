package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.DiseaseRecord;
import org.springframework.stereotype.Service;

@Service
public interface DiseaseRecordService extends BaseService<DiseaseRecord>{
    int deleteByImgPath(String imgPath);
}
