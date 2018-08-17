package com.prostate.stata.service.impl;

import com.prostate.stata.beans.CityBean;
import com.prostate.stata.beans.CityDetailBean;
import com.prostate.stata.entity.City;
import com.prostate.stata.mapper.master.CityWriteMapper;
import com.prostate.stata.mapper.slaver.CityReadMapper;
import com.prostate.stata.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityWriteMapper cityWriteMapper;

    @Autowired
    private CityReadMapper cityReadMapper;
    @Override
    public int insertSelective(City city) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(City city) {
        return 0;
    }

    @Override
    public City selectById(String id) {
        return cityReadMapper.selectById(id);
    }

    @Override
    public List<City> selectByParams(City city) {
        return cityReadMapper.selectByParams(city);
    }

    @Cacheable(value = "citylist", key = "'city_'+'county'")
    @Override
    public CityBean getCounty(City city) {
        return cityReadMapper.getCounty(city);
    }

    @Cacheable(value = "city", key = "'city_'+#id",unless="#result == null")
    @Override
    public City getById(String id) {
        return cityReadMapper.getById(id);
    }

    @Override
    public CityDetailBean getCityDetail(String cityId) {
        CityDetailBean cityDetailBean = cityReadMapper.getCityDetail(cityId);

        if (cityDetailBean!=null){
            return cityDetailBean;
        }
        return null;
    }
}
