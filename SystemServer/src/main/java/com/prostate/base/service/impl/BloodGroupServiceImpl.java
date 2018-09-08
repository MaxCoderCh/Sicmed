package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.BloodGroupReadMapper;
import com.prostate.base.mapper.master.BloodGroupWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.BloodGroupDO;
import com.prostate.base.service.BloodGroupService;



@Service
public class BloodGroupServiceImpl implements BloodGroupService {

	@Autowired
	private BloodGroupWriteMapper bloodGroupWriteMapper;

	@Autowired
	private BloodGroupReadMapper bloodGroupReadMapper;

	@Override
	public BloodGroupDO get(String id){
		return bloodGroupReadMapper.get(id);
	}

	@Override
	public BloodGroupDO getByName(String bloodGroupName) {
		return bloodGroupReadMapper.getByName(bloodGroupName);
	}

	@Override
	public BloodGroupDO getByNumBer(String bloodGroupNumber) {
		return bloodGroupReadMapper.getByNumBer(bloodGroupNumber);
	}

	@Override
	public List<BloodGroupDO> list(Map<String, Object> map){
		return bloodGroupReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return bloodGroupReadMapper.count(map);
	}
	
	@Override
	public int save(BloodGroupDO bloodGroup){
		return bloodGroupWriteMapper.save(bloodGroup);
	}
	
	@Override
	public int update(BloodGroupDO bloodGroup){
		return bloodGroupWriteMapper.update(bloodGroup);
	}


	@Override
	public int remove(String id){
		return bloodGroupWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return bloodGroupWriteMapper.batchRemove(ids);
	}
	
}
