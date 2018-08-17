package com.prostate.stata.service;

import com.prostate.stata.entity.LableInquiry;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LableInquiryService extends BaseService<LableInquiry> {

    Map<String,String> getLableInquiryJson();
}
