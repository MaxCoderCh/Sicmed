package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.HospitalReadMapper;
import com.prostate.base.mapper.master.HospitalWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.HospitalDO;
import com.prostate.base.service.HospitalService;



@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalWriteMapper hospitalWriteMapper;

	@Autowired
	private HospitalReadMapper hospitalReadMapper;
	
	@Override
	public HospitalDO get(String id){
		return hospitalReadMapper.get(id);
	}

	@Override
	public HospitalDO getByName(String hospitalName) {
		return hospitalReadMapper.getByName(hospitalName);
	}

//	@Override
//	public HospitalDO getByNumber(String hospitalNumber) {
//		return hospitalReadMapper.getByNumber(hospitalNumber);
//	}

	@Override
	public List<HospitalDO> list(Map<String, Object> map){
		return hospitalReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return hospitalReadMapper.count(map);
	}
	
	@Override
	public int save(HospitalDO hospital){
		return hospitalWriteMapper.save(hospital);
	}
	
	@Override
	public int update(HospitalDO hospital){
		return hospitalWriteMapper.update(hospital);
	}
	
	@Override
	public int remove(String id){
		return hospitalWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return hospitalWriteMapper.batchRemove(ids);
	}
	
}
