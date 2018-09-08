package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.AnamnesisIllnessReadMapper;
import com.prostate.base.mapper.master.AnamnesisIllnessWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.AnamnesisIllnessDO;
import com.prostate.base.service.AnamnesisIllnessService;



@Service
public class AnamnesisIllnessServiceImpl implements AnamnesisIllnessService {

	@Autowired
	private AnamnesisIllnessWriteMapper anamnesisIllnessWriteMapper;

	@Autowired
	private AnamnesisIllnessReadMapper anamnesisIllnessReadMapper;
	
	@Override
	public AnamnesisIllnessDO get(String id){
		return anamnesisIllnessReadMapper.get(id);
	}
	
	@Override
	public List<AnamnesisIllnessDO> list(Map<String, Object> map){
		return anamnesisIllnessReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return anamnesisIllnessReadMapper.count(map);
	}
	
	@Override
	public int save(AnamnesisIllnessDO anamnesisIllness){
		return anamnesisIllnessWriteMapper.save(anamnesisIllness);
	}
	
	@Override
	public int update(AnamnesisIllnessDO anamnesisIllness){
		return anamnesisIllnessWriteMapper.update(anamnesisIllness);
	}
	
	@Override
	public int remove(String id){
		return anamnesisIllnessWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return anamnesisIllnessWriteMapper.batchRemove(ids);
	}

	@Override
	public List<AnamnesisIllnessDO> listByName(String name) {
		return anamnesisIllnessReadMapper.listByName(name);
	}

	@Override
	public List<AnamnesisIllnessDO> listByNumber(String number) {
		return anamnesisIllnessReadMapper.listByNumber(number);
	}

}
