package com.sicmed.assessmen.mapper.pomr.write;

import com.sicmed.assessmen.entity.HospitalRecord;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface HospitalRecordWriteMapper extends BaseWriteMapper<HospitalRecord> {

    int deleteByImgPath(String imgPath);
}