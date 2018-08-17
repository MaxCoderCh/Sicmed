package com.prostate.stata.service.impl;

import com.prostate.stata.entity.DoctorTitle;
import com.prostate.stata.mapper.master.DoctorTitleWriteMapper;
import com.prostate.stata.mapper.slaver.DoctorTitleReadMapper;
import com.prostate.stata.service.DoctorTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorTitleServiceImpl implements DoctorTitleService {

    @Autowired
    private DoctorTitleWriteMapper doctorTitleWriteMapper;



    @Autowired
    private DoctorTitleReadMapper doctorTitleReadMapper;


    @Cacheable(value = "JSON_DOCTOR_TITLE",key = "'json_data'")
    @Override
    public Map<String, String> getDoctorTitleJson() {
        Map<String, String> map = new LinkedHashMap<>();
        List<DoctorTitle> doctorTitles = doctorTitleReadMapper.selectByParams(null);

        if (doctorTitles.size() == 0 || doctorTitles == null) {
            return null;
        }
        for (DoctorTitle doctorTitle : doctorTitles) {
            map.put(doctorTitle.getId(), doctorTitle.getDoctorTitleName());
        }
        return map;
    }

    @Override
    public int insertSelective(DoctorTitle doctorTitle) {
        return 0;
    }

    @Override
    public int updateSelective(DoctorTitle doctorTitle) {
        return 0;
    }

    @Override
    public DoctorTitle selectById(String id) {
        return doctorTitleReadMapper.selectById(id);
    }

    @Override
    public List<DoctorTitle> selectByParams(DoctorTitle doctorTitle) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }
}
