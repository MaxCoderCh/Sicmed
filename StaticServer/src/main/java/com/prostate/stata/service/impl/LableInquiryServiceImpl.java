package com.prostate.stata.service.impl;

import com.prostate.stata.entity.LableInquiry;
import com.prostate.stata.mapper.master.LableInquiryWriteMapper;
import com.prostate.stata.mapper.slaver.LableInquiryReadMapper;
import com.prostate.stata.service.LableInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LableInquiryServiceImpl implements LableInquiryService {

    @Autowired
    private LableInquiryWriteMapper lableInquiryWriteMapper;

    @Autowired
    private LableInquiryReadMapper lableInquiryReadMapper;

    @Override
    public int insertSelective(LableInquiry lableInquiry) {
        return 0;
    }

    @Override
    public int updateSelective(LableInquiry lableInquiry) {
        return 0;
    }

    @Override
    public LableInquiry selectById(String id) {
        return null;
    }

    @Override
    public List<LableInquiry> selectByParams(LableInquiry lableInquiry) {
        return lableInquiryReadMapper.selectByParams(lableInquiry);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public Map<String, String> getLableInquiryJson() {
        Map<String, String> lableInquiryMap = new LinkedHashMap<>();
        List<LableInquiry> lableInquiryList = lableInquiryReadMapper.selectByParams(null);

        if (lableInquiryList.size() == 0 || lableInquiryList == null) {
            return null;
        }
        for (LableInquiry lableInquiry : lableInquiryList) {
            lableInquiryMap.put(lableInquiry.getId(), lableInquiry.getLableName());
        }
        return lableInquiryMap;
    }
}
