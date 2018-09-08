package com.prostate.base.vo;

import com.prostate.base.domain.CityDO;

import java.io.Serializable;

/**
 * @Author: bianyakun
 * @Date: 2018/5/21 10:39
 * @Todo:  城市类型的vo   包括省市县三级信心
 */

public class CityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CityDO county;

    private CityDO city;

    private CityDO province;



    public CityDO getCounty() {
        return county;
    }

    public void setCounty(CityDO county) {
        this.county = county;
    }

    public CityDO getCity() {
        return city;
    }

    public void setCity(CityDO city) {
        this.city = city;
    }

    public CityDO getProvince() {
        return province;
    }

    public void setProvince(CityDO province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "CityVO{" +
                "county=" + county +
                ", city=" + city +
                ", province=" + province +
                '}';
    }
}
