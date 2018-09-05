package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Docket;
import com.prostate.stata.entity.DocketConstant;
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

        Docket docketOld = this.getByParam(docket);
        if (docketOld != null) {
            docket.setId(docketOld.getId());
            docket.setDocketStatus(DocketConstant.USABLE);
            return this.updateSelective(docket);
        }
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
    public int logicDelete(Docket docket) {
        return docketWriteMapper.logicDelete(docket);
    }

    @Override
    public Docket getByParam(Docket docket) {
        return docketReadMapper.getByParam(docket);
    }
}
