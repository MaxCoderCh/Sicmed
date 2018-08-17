package com.prostate.assessmen.mapper.pomr.write;

import com.prostate.assessmen.entity.DiseaseRecord;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface DiseaseRecordWriteMapper extends BaseWriteMapper<DiseaseRecord> {

    int deleteByImgPath(String imgPath);
}