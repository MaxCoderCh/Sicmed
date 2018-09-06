package com.sicmed.statistic.mapper.slaver;

import com.sicmed.statistic.entity.Statistic;

public interface StatisticReadMapper extends BaseReadMapper<Statistic>{

    Statistic getByParam(Statistic statistic);
}