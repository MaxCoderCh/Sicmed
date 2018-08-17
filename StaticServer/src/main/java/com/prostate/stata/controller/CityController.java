package com.prostate.stata.controller;

import com.prostate.stata.beans.CityBean;
import com.prostate.stata.beans.CityDetailBean;
import com.prostate.stata.entity.City;
import com.prostate.stata.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "city")
public class CityController extends BaseController {

    @Autowired
    private CityService cityService;


    /**
     * 获取省份列表
     *
     * @return
     */
    @PostMapping(value = "getCounty")
    public Map getCounty() {
        resultMap = new LinkedHashMap<>();
        //查询参数
        City city = new City();
        //查询参数赋值

        //调用service 查询数据
        CityBean cityBean = cityService.getCounty(city);
        //查询结果校验 组装 响应
        if (cityBean != null) {
            return querySuccessResponse(cityBean.getCityList());
        }

        return queryEmptyResponse();
    }

    /**
     * 根据上级城市ID查询城市列表
     *
     * @return
     */
    @GetMapping(value = "getCityByParentId")
    public Map getCityByParentId(String parentId) {
        //查询参数
        City city = new City();
        city.setParentCityId(parentId);
        //查询参数赋值

        //调用service 查询数据
        List<City> cityList = cityService.selectByParams(city);
        //查询结果校验 组装 响应
        if (cityList.size() > 0) {
            return querySuccessResponse(cityList);
        }
        return queryEmptyResponse();
    }

    /**
     * 根据上级城市ID查询城市列表
     *
     * @return
     */
    @PostMapping(value = "getById")
    public Map getById(String id) {


        //调用service 查询数据
        City city = cityService.getById(id);
        //查询结果校验 组装 响应
        return querySuccessResponse(city);
    }

    /**
     * 根据 区县 级  ID 查询 城市详细名称
     *
     * @param cityId
     * @return
     */
    @GetMapping(value = "getCityDetail")
    public Map getCityDetail(String cityId) {

        CityDetailBean cityDetailBean = cityService.getCityDetail(cityId);

        if (cityDetailBean != null) {
            return querySuccessResponse(cityDetailBean);
        }
        return queryEmptyResponse();
    }

}
