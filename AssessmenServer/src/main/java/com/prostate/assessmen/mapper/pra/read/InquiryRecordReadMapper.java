package com.prostate.assessmen.mapper.pra.read;

import com.prostate.assessmen.entity.InquiryRecord;
import com.prostate.assessmen.mapper.BaseReadMapper;

public interface InquiryRecordReadMapper extends BaseReadMapper<InquiryRecord> {

    InquiryRecord selectByArchive(String archive);
}