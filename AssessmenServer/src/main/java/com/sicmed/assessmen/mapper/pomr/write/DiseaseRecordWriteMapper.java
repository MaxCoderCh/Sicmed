package com.sicmed.assessmen.mapper.pomr.write;

import com.sicmed.assessmen.entity.DiseaseRecord;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface DiseaseRecordWriteMapper extends BaseWriteMapper<DiseaseRecord> {

    int deleteByImgPath(String imgPath);
}