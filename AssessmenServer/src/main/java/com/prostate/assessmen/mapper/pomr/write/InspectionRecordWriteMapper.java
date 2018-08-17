package com.prostate.assessmen.mapper.pomr.write;

import com.prostate.assessmen.entity.InspectionRecord;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface InspectionRecordWriteMapper extends BaseWriteMapper<InspectionRecord> {

    int deleteByImgPath(String imgPath);
}