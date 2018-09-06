package com.sicmed.statistic.service.impl;

import com.sicmed.statistic.entity.Statistic;
import com.sicmed.statistic.mapper.master.StatisticWriteMapper;
import com.sicmed.statistic.mapper.slaver.StatisticReadMapper;
import com.sicmed.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticWriteMapper statisticWriteMapper;

    @Autowired
    private StatisticReadMapper statisticReadMapper;

    @Override
    public int insertSelective(Statistic statistic) {
        return statisticWriteMapper.insertSelective(statistic);
    }

    @Override
    public int updateSelective(Statistic statistic) {
        return statisticWriteMapper.updateSelective(statistic);
    }

    @Override
    public Statistic selectById(String id) {
        return statisticReadMapper.selectById(id);
    }

    @Override
    public List<Statistic> selectByParams(Statistic statistic) {
        return statisticReadMapper.selectByParams(statistic);
    }

    @Override
    public int deleteById(String id) {
        return statisticWriteMapper.deleteById(id);
    }

    @Override
    public Statistic getByParam(Statistic statistic) {
        return statisticReadMapper.getByParam(statistic);
    }
}
