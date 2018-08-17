package com.prostate.stata.beans;

import com.prostate.stata.entity.City;

import java.util.List;

public class CityBean extends City {

    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
