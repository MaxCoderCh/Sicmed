package com.prostate.assessmen.mapper.pomr.write;

import com.prostate.assessmen.entity.CheckupRecord;
import com.prostate.assessmen.mapper.BaseWriteMapper;

public interface CheckupRecordWriteMapper extends BaseWriteMapper<CheckupRecord> {

    int deleteByImgPath(String imgPath);
}