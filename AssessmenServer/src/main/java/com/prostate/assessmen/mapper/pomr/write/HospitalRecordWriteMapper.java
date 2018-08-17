package com.prostate.assessmen.mapper.pomr.write;

import com.prostate.assessmen.entity.HospitalRecord;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface HospitalRecordWriteMapper extends BaseWriteMapper<HospitalRecord> {

    int deleteByImgPath(String imgPath);
}