package com.sicmed.assessmen.mapper.pra.read;

import com.sicmed.assessmen.entity.InquiryRecord;
import com.sicmed.assessmen.mapper.BaseReadMapper;

public interface InquiryRecordReadMapper extends BaseReadMapper<InquiryRecord> {

    InquiryRecord selectByArchive(String archive);
}