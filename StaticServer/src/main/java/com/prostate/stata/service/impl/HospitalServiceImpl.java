package com.prostate.stata.service.impl;

import com.prostate.stata.entity.Hospital;
import com.prostate.stata.mapper.master.HospitalWriteMapper;
import com.prostate.stata.mapper.slaver.HospitalReadMapper;
import com.prostate.stata.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalWriteMapper hospitalWriteMapper;

    @Autowired
    private HospitalReadMapper hospitalReadMapper;



    /**
     *@Author:      ykbian
     *@date_time:   2018/8/6 10:50
     *@Description:  返回医院的所有信息
     *@param:
     */
    @Cacheable(value = "JSON_HOSPITAL",key = "'json_data'")
    @Override
    public Map<String, String> getHospitalJson() {
        Map<String, String> map = new LinkedHashMap<>();
        List<Hospital> hospitals = hospitalReadMapper.selectByParams(null);

        if (hospitals.size() == 0 || hospitals == null) {
            return null;
        }
        for (Hospital hospital : hospitals) {
            map.put(hospital.getId(), hospital.getHospitalName());
        }
        return map;
    }


    @Override
    public int insertSelective(Hospital hospital) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int updateSelective(Hospital hospital) {
        return 0;
    }

    @Override
    public Hospital selectById(String id) {
        return hospitalReadMapper.selectById(id);
    }

    @Override
    public List<Hospital> selectByParams(Hospital hospital) {
        return null;
    }
}
