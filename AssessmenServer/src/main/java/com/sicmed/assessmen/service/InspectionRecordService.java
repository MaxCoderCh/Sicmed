package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.InspectionRecord;
import org.springframework.stereotype.Service;

@Service
public interface InspectionRecordService extends BaseService<InspectionRecord> {
    int deleteByImgPath(String imgPath);
}
