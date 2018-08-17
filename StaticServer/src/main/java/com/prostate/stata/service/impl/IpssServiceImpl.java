package com.prostate.stata.service.impl;

import com.prostate.stata.beans.IpssBean;
import com.prostate.stata.entity.Ipss;
import com.prostate.stata.mapper.master.IpssWriteMapper;
import com.prostate.stata.mapper.slaver.IpssReadMapper;
import com.prostate.stata.service.IpssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpssServiceImpl implements IpssService {

    @Autowired
    private IpssWriteMapper ipssWriteMapper;

    @Autowired
    private IpssReadMapper ipssReadMapper;

    @Override
    public int insertSelective(Ipss ipss) {
        return 0;
    }

    @Override
    public int updateSelective(Ipss ipss) {
        return 0;
    }

    @Override
    public Ipss selectById(String id) {
        return null;
    }

    @Override
    public List<Ipss> selectByParams(Ipss ipss) {
        return ipssReadMapper.selectByParams(ipss);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Cacheable(value = "scale", key = "'scale_'+'ipss'")
    @Override
    public List<IpssBean> selectByParamss(Ipss ipss) {
        return ipssReadMapper.selectByParamss(ipss);
    }
}
