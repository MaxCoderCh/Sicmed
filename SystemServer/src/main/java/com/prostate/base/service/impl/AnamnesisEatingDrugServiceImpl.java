package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.AnamnesisEatingDrugReadMapper;
import com.prostate.base.mapper.master.AnamnesisEatingDrugWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.AnamnesisEatingDrugDO;
import com.prostate.base.service.AnamnesisEatingDrugService;



@Service
public class AnamnesisEatingDrugServiceImpl implements AnamnesisEatingDrugService {

	@Autowired
	private AnamnesisEatingDrugWriteMapper anamnesisEatingDrugWriteMapper;

	@Autowired
	private AnamnesisEatingDrugReadMapper anamnesisEatingDrugReadMapper;
	
	@Override
	public AnamnesisEatingDrugDO get(String id){
		return anamnesisEatingDrugReadMapper.get(id);
	}

	@Override
	public AnamnesisEatingDrugDO getByName(String eatingDrugName) {
		return anamnesisEatingDrugReadMapper.getByName(eatingDrugName);
	}

	@Override
	public AnamnesisEatingDrugDO getByNumber(String eatingDrugNumber) {
		return anamnesisEatingDrugReadMapper.getByNumber(eatingDrugNumber);
	}

	@Override
	public List<AnamnesisEatingDrugDO> list(Map<String, Object> map){
		return anamnesisEatingDrugReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return anamnesisEatingDrugReadMapper.count(map);
	}
	
	@Override
	public int save(AnamnesisEatingDrugDO anamnesisEatingDrug){
		return anamnesisEatingDrugWriteMapper.save(anamnesisEatingDrug);
	}
	
	@Override
	public int update(AnamnesisEatingDrugDO anamnesisEatingDrug){
		return anamnesisEatingDrugWriteMapper.update(anamnesisEatingDrug);
	}
	
	@Override
	public int remove(String id){
		return anamnesisEatingDrugWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return anamnesisEatingDrugWriteMapper.batchRemove(ids);
	}
	
}
