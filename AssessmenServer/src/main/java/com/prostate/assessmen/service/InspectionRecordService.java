package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.InspectionRecord;
import org.springframework.stereotype.Service;

@Service
public interface InspectionRecordService extends BaseService<InspectionRecord> {
    int deleteByImgPath(String imgPath);
}
