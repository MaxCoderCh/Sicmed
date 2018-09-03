package com.sicmed.assessmen.mapper.slaver;

import com.sicmed.assessmen.entity.InquiryRecord;

public interface InquiryRecordReadMapper extends BaseReadMapper<InquiryRecord> {

    InquiryRecord selectByArchive(String archive);
}