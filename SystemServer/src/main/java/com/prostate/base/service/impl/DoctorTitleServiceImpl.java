package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.DoctorTitleReadMapper;
import com.prostate.base.mapper.master.DoctorTitleWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.DoctorTitleDO;
import com.prostate.base.service.DoctorTitleService;



@Service
public class DoctorTitleServiceImpl implements DoctorTitleService {


	@Autowired
	private DoctorTitleWriteMapper doctorTitleWriteMapper;

	@Autowired
	private DoctorTitleReadMapper doctorTitleReadMapper;

	
	@Override
	public DoctorTitleDO get(String id){
		return doctorTitleReadMapper.get(id);
	}

	@Override
	public DoctorTitleDO getByName(String doctorTitleName) {
		return doctorTitleReadMapper.getByName(doctorTitleName);
	}

	@Override
	public DoctorTitleDO getByNumber(String doctorTitleNumber) {
		return doctorTitleReadMapper.getByNumber(doctorTitleNumber);
	}

	@Override
	public List<DoctorTitleDO> list(Map<String, Object> map){
		return doctorTitleReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return doctorTitleReadMapper.count(map);
	}
	
	@Override
	public int save(DoctorTitleDO doctorTitle){
		return doctorTitleWriteMapper.save(doctorTitle);
	}
	
	@Override
	public int update(DoctorTitleDO doctorTitle){
		return doctorTitleWriteMapper.update(doctorTitle);
	}
	
	@Override
	public int remove(String id){
		return doctorTitleWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return doctorTitleWriteMapper.batchRemove(ids);
	}
	
}
