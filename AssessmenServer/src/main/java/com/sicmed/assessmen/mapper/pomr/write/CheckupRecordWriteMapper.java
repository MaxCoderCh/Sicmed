package com.sicmed.assessmen.mapper.pomr.write;

import com.sicmed.assessmen.entity.CheckupRecord;
import com.sicmed.assessmen.mapper.BaseWriteMapper;

public interface CheckupRecordWriteMapper extends BaseWriteMapper<CheckupRecord> {

    int deleteByImgPath(String imgPath);
}