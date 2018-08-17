package com.prostate.stata.service.impl;

import com.prostate.stata.entity.LablePrice;
import com.prostate.stata.mapper.master.LablePriceWriteMapper;
import com.prostate.stata.mapper.slaver.LablePriceReadMapper;
import com.prostate.stata.service.LablePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LablePriceServiceImpl implements LablePriceService {
    @Autowired
    private LablePriceWriteMapper lablePriceWriteMapper;

    @Autowired
    private LablePriceReadMapper lablePriceReadMapper;

    @Override
    public int insertSelective(LablePrice lablePrice) {
        return lablePriceWriteMapper.insertSelective(lablePrice);
    }

    @Override
    public int updateSelective(LablePrice lablePrice) {
        return lablePriceWriteMapper.updateSelective(lablePrice);
    }

    @Override
    public LablePrice selectById(String id) {
        return lablePriceReadMapper.selectById(id);
    }

    @Override
    public List<LablePrice> selectByParams(LablePrice lablePrice) {
        return lablePriceReadMapper.selectByParams(lablePrice);
    }

    @Override
    public int deleteById(String id) {
        return lablePriceWriteMapper.deleteById(id);
    }

    @Override
    public Map<String, String> getLablePriceJson(LablePrice lablePrice) {
        Map<String, String> map = new LinkedHashMap<>();
        List<LablePrice> lablePrices = lablePriceReadMapper.selectByToken(lablePrice);
        if (lablePrices.size() == 0|| lablePrices == null ) {
            return null;
        }
        for (LablePrice lablePrice1 : lablePrices) {
            map.put(lablePrice1.getId(), lablePrice1.getLableName());
        }
        return map;
    }

    @Override
    public List<LablePrice> selectByToken(LablePrice lablePrice) {
        return lablePriceReadMapper.selectByToken(lablePrice);
    }
}
