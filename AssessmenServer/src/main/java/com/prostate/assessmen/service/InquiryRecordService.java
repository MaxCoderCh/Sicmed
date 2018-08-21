package com.prostate.assessmen.service;

import com.prostate.assessmen.entity.InquiryRecord;

public interface InquiryRecordService extends BaseService<InquiryRecord>{
    InquiryRecord selectByArchive(String archive);
}
