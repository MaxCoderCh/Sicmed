package com.sicmed.statistic.service;

import com.sicmed.statistic.entity.Statistic;

public interface StatisticService extends BaseService<Statistic> {
    Statistic getByParam(Statistic statistic);
}
