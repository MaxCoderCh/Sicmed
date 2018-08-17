package com.prostate.stata.service;

import com.prostate.stata.entity.LablePrice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LablePriceService extends BaseService<LablePrice> {
    List<LablePrice> selectByToken(LablePrice lablePrice);

    Map<String,String> getLablePriceJson(LablePrice lablePrice);
}
