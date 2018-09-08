package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.EducationReadMapper;
import com.prostate.base.mapper.master.EducationWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.EducationDO;
import com.prostate.base.service.EducationService;



@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationWriteMapper educationWriteMapper;

	@Autowired
	private EducationReadMapper educationReadMapper;
	
	@Override
	public EducationDO get(String id){
		return educationReadMapper.get(id);
	}

	@Override
	public EducationDO getByName(String educationName) {
		return educationReadMapper.getByName(educationName);
	}

	@Override
	public EducationDO getByNumber(String educationNumber) {
		return educationReadMapper.getByNumber(educationNumber);
	}

	@Override
	public List<EducationDO> list(Map<String, Object> map){
		return educationReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return educationReadMapper.count(map);
	}
	
	@Override
	public int save(EducationDO education){
		return educationWriteMapper.save(education);
	}
	
	@Override
	public int update(EducationDO education){
		return educationWriteMapper.update(education);
	}
	
	@Override
	public int remove(String id){
		return educationWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return educationWriteMapper.batchRemove(ids);
	}
	
}
