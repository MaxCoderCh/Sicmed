package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.HospitalRecord;
import org.springframework.stereotype.Service;

@Service
public interface HospitalRecordService extends BaseService<HospitalRecord>{
    int deleteByImgPath(String imgPath);
}
