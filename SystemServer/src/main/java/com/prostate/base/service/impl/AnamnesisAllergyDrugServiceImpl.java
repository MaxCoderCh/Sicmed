package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.AnamnesisAllergyDrugReadMapper;
import com.prostate.base.mapper.master.AnamnesisAllergyDrugWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.AnamnesisAllergyDrugDO;
import com.prostate.base.service.AnamnesisAllergyDrugService;



@Service
public class AnamnesisAllergyDrugServiceImpl implements AnamnesisAllergyDrugService {
	@Autowired
	private AnamnesisAllergyDrugWriteMapper anamnesisAllergyDrugWriteMapper;

	@Autowired
	private AnamnesisAllergyDrugReadMapper anamnesisAllergyDrugReadMapper;


	
	@Override
	public AnamnesisAllergyDrugDO get(String id){
		return anamnesisAllergyDrugReadMapper.get(id);
	}
	
	@Override
	public List<AnamnesisAllergyDrugDO> list(Map<String, Object> map){
		return anamnesisAllergyDrugReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return anamnesisAllergyDrugReadMapper.count(map);
	}
	
	@Override
	public int save(AnamnesisAllergyDrugDO anamnesisAllergyDrug){
		return anamnesisAllergyDrugWriteMapper.save(anamnesisAllergyDrug);
	}
	
	@Override
	public int update(AnamnesisAllergyDrugDO anamnesisAllergyDrug){
		return anamnesisAllergyDrugWriteMapper.update(anamnesisAllergyDrug);
	}
	
	@Override
	public int remove(String id){
		return anamnesisAllergyDrugWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return anamnesisAllergyDrugWriteMapper.batchRemove(ids);
	}
	
}
