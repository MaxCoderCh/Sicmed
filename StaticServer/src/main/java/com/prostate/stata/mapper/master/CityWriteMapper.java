package com.prostate.stata.mapper.master;

import com.prostate.stata.beans.CityBean;
import com.prostate.stata.entity.City;

public interface CityWriteMapper extends BaseWriteMapper<City> {

    CityBean getCounty(City city);

    City getById(String id);
}