package com.sicmed.statistic.service;

public interface FeignService {

    String OrderServerGetAcceptedOrderCount(String userId) throws Exception;

    String OrderServerGetAcceptedTurnOrderCount(String userId) throws Exception;

    String OrderServerGetAcceptedTurnPatientCount(String userId) throws Exception;
}

