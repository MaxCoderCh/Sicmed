package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.CheckupRecord;
import org.springframework.stereotype.Service;

@Service
public interface CheckupRecordService extends BaseService<CheckupRecord>{
    int deleteByImgPath(String imgPath);
}
