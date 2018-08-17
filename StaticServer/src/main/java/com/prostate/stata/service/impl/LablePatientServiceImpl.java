package com.prostate.stata.service.impl;

import com.prostate.stata.entity.LablePatient;
import com.prostate.stata.mapper.master.LablePatientWriteMapper;
import com.prostate.stata.mapper.slaver.LablePatientReadMapper;
import com.prostate.stata.service.LablePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LablePatientServiceImpl implements LablePatientService {
    @Autowired
    private LablePatientWriteMapper lablePatientWriteMapper;

    @Autowired
    private LablePatientReadMapper lablePatientReadMapper;

    @Override
    public int insertSelective(LablePatient lablePatient) {
        return lablePatientWriteMapper.insertSelective(lablePatient);
    }

    @Override
    public Map<String, String> getDoctorTitleJson(LablePatient lablePatient) {
        Map<String, String> map = new LinkedHashMap<>();
        List<LablePatient> lablePatients = lablePatientReadMapper.selectByToken(lablePatient);
        if (lablePatients.size() == 0|| lablePatients == null ) {
            return null;
        }
        for (LablePatient lablePatient1 : lablePatients) {
            map.put(lablePatient1.getId(), lablePatient1.getLableName());
        }
        return map;
    }

    @Override
    public int updateSelective(LablePatient lablePatient) {
        return 0;
    }

    @Override
    public LablePatient selectById(String id) {
        return null;
    }

    @Override
    public List<LablePatient> selectByParams(LablePatient lablePatient) {
        return null;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public List<LablePatient> selectByToken(LablePatient lablePatient) {
        return lablePatientReadMapper.selectByToken(lablePatient);
    }
}
