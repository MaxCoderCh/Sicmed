package com.sicmed.assessmen.mapper.pomr.write;

import com.sicmed.assessmen.entity.InspectionRecord;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface InspectionRecordWriteMapper extends BaseWriteMapper<InspectionRecord> {

    int deleteByImgPath(String imgPath);
}