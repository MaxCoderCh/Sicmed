package com.prostate.base.service;

import com.prostate.base.domain.CityDO;

import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:21 2018/5/14
 */
public interface ProvinceAndCityManageService {

    CityDO get(String id);

    List<CityDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CityDO cityDO);

    int update(CityDO cityDO);

    int remove(String id);

    int batchRemove(String[] ids);

    List<CityDO> listByName(String name);

    List<CityDO> listByNumber(String number);
}
