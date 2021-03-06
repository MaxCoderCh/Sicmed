package com.prostate.record.service.impl;

import com.prostate.record.beans.WeChatPatientBean;
import com.prostate.record.entity.UserPatient;
import com.prostate.record.mapper.slaver.UserPatientReadMapper;
import com.prostate.record.mapper.master.UserPatientWriteMapper;
import com.prostate.record.service.UserPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPatientServiceImpl implements UserPatientService {
    @Autowired
    private UserPatientWriteMapper userPatientWriteMapper;

    @Autowired
    private UserPatientReadMapper userPatientReadMapper;

    @Override
    public int insertSelective(UserPatient userPatient) {
        return userPatientWriteMapper.insertSelective(userPatient);
    }

    @Override
    public int updateSelective(UserPatient userPatient) {
        return userPatientWriteMapper.updateSelective(userPatient);
    }

    @Override
    public UserPatient selectById(String id) {
        return null;
    }

    @Override
    public List<UserPatient> selectByParams(UserPatient userPatient) {
        return userPatientReadMapper.selectByParams(userPatient);
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    /**
     * 创建用户患者关系
     *
     * @param patientId
     * @param token
     * @param patientSource
     * @return
     */
    @Override
    public int addUserPatient(String patientId, String token, String patientSource) {
        UserPatient userPatient = new UserPatient();
        userPatient.setPatientId(patientId);
        userPatient.setUserId(token);
        userPatient.setPatientSource(patientSource);
        return userPatientWriteMapper.insertSelective(userPatient);
    }

    @Override
    public List<WeChatPatientBean> getPatientList(UserPatient userPatient) {
        return userPatientReadMapper.getPatientList(userPatient);
    }

    @Override
    public int removeByParams(UserPatient userPatient) {
        return userPatientWriteMapper.removeByParams(userPatient);
    }

    @Override
    public int updateByParams(UserPatient userPatient) {
        return userPatientWriteMapper.updateByParams(userPatient);
    }

    @Override
    public UserPatient getByPatientIdAndToken(UserPatient userPatient) {
        return userPatientReadMapper.getByPatientIdAndToken(userPatient);
    }

    @Override
    public int selectCountByParams(UserPatient userPatient) {
        return userPatientReadMapper.selectCountByParams(userPatient);
    }

    @Override
    public String countByParams(UserPatient userPatient) {
        return userPatientReadMapper.countByParams(userPatient);
    }

    @Override
    public List<WeChatPatientBean> getAcceptedTurnPatientList(UserPatient userPatient) {
        return userPatientReadMapper.getAcceptedTurnPatientList(userPatient);
    }

    @Override
    public int deleteByParam(UserPatient oldUserPatient) {
        return userPatientWriteMapper.deleteByParam(oldUserPatient);
    }

}
