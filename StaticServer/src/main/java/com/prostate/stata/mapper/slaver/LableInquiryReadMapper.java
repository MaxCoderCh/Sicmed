package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.LableInquiry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LableInquiryReadMapper extends BaseReadMapper<LableInquiry>{

    @Override
    LableInquiry selectById(String id);

    @Override
    List<LableInquiry> selectByParams(LableInquiry e);

}