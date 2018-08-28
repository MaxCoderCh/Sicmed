package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Docket;
import com.prostate.stata.mapper.master.DocketWriteMapper;
import com.prostate.stata.mapper.slaver.DocketReadMapper;
import com.prostate.stata.service.DocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocketServiceImpl implements DocketService {

    @Autowired
    private DocketWriteMapper docketWriteMapper;

    @Autowired
    private DocketReadMapper docketReadMapper;

    @Override
    public int insertSelective(Docket docket) {
        return docketWriteMapper.insertSelective(docket);
    }

    @Override
    public int updateSelective(Docket docket) {
        return docketWriteMapper.updateSelective(docket);
    }

    @Override
    public Docket selectById(String id) {
        return docketReadMapper.selectById(id);
    }

    @Override
    public List<Docket> selectByParams(Docket docket) {
        return docketReadMapper.selectByParams(docket);
    }

    @Override
    public int deleteById(String id) {
        return docketWriteMapper.deleteById(id);
    }

    @Override
    public int falseDeleteById(Docket docket) {
        return docketWriteMapper.falseDeleteById(docket);
    }
}
