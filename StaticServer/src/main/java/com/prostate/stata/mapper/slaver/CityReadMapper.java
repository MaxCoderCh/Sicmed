package com.prostate.stata.mapper.slaver;

import com.prostate.stata.beans.CityBean;
import com.prostate.stata.beans.CityDetailBean;
import com.prostate.stata.entity.City;

public interface CityReadMapper extends BaseReadMapper<City> {

    CityBean getCounty(City city);

    City getById(String id);

    CityDetailBean getCityDetail(String cityId);
}