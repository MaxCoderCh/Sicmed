package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.AnamnesisTypeReadMapper;
import com.prostate.base.mapper.master.AnamnesisTypeWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.AnamnesisTypeDO;
import com.prostate.base.service.AnamnesisTypeService;



@Service
public class AnamnesisTypeServiceImpl implements AnamnesisTypeService {

	@Autowired
	private AnamnesisTypeWriteMapper anamnesisTypeWriteMapper;

	@Autowired
	private AnamnesisTypeReadMapper anamnesisTypeReadMapper;
	
	@Override
	public AnamnesisTypeDO get(String id){
		return anamnesisTypeReadMapper.get(id);
	}
	
	@Override
	public List<AnamnesisTypeDO> list(Map<String, Object> map){
		return anamnesisTypeReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return anamnesisTypeReadMapper.count(map);
	}
	
	@Override
	public int save(AnamnesisTypeDO anamnesisType){
		return anamnesisTypeWriteMapper.save(anamnesisType);
	}
	
	@Override
	public int update(AnamnesisTypeDO anamnesisType){
		return anamnesisTypeWriteMapper.update(anamnesisType);
	}
	
	@Override
	public int remove(String id){
		return anamnesisTypeWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return anamnesisTypeWriteMapper.batchRemove(ids);
	}

	@Override
	public List<AnamnesisTypeDO> listByName(String name) {
		return anamnesisTypeReadMapper.listByName(name);
	}

	@Override
	public List<AnamnesisTypeDO> listByNumber(String number) {
		return anamnesisTypeReadMapper.listByNumber(number);
	}

}
