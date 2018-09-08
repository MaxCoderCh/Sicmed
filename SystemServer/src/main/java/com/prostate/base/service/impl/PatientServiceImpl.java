package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.PatientReadMapper;
import com.prostate.base.mapper.master.PatientWriteMapper;
import com.prostate.base.domain.PatientDO;
import com.prostate.base.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientWriteMapper patientWriteMapper;

	@Autowired
	private PatientReadMapper patientReadMapper;

	@Override
	public PatientDO get(String id){
		return patientReadMapper.get(id);
	}
	
	@Override
	public List<PatientDO> list(Map<String, Object> map){
		return patientReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return patientReadMapper.count(map);
	}
	
	@Override
	public int save(PatientDO patient){
		return patientWriteMapper.save(patient);
	}
	
	@Override
	public int update(PatientDO patient){
		return patientWriteMapper.update(patient);
	}
	
	@Override
	public int remove(String id){
		return patientWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return patientWriteMapper.batchRemove(ids);
	}
	
}
