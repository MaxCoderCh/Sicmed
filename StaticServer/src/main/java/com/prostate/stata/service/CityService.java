package com.prostate.stata.service;

import com.prostate.stata.beans.CityBean;
import com.prostate.stata.beans.CityDetailBean;
import com.prostate.stata.entity.City;
import org.springframework.stereotype.Service;

@Service
public interface CityService extends BaseService<City> {

    CityBean getCounty(City city);

    City getById(String id);

    CityDetailBean getCityDetail(String cityId);
}
