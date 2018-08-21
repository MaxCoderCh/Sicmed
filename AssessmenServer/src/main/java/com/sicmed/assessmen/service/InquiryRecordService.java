package com.sicmed.assessmen.service;

import com.sicmed.assessmen.entity.InquiryRecord;

public interface InquiryRecordService extends BaseService<InquiryRecord>{
    InquiryRecord selectByArchive(String archive);
}
