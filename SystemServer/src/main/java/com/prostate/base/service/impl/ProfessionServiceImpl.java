package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.ProfessionReadMapper;
import com.prostate.base.mapper.master.ProfessionWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.ProfessionDO;
import com.prostate.base.service.ProfessionService;



@Service
public class ProfessionServiceImpl implements ProfessionService {

	@Autowired
	private ProfessionWriteMapper professionWriteMapper;

	@Autowired
	private ProfessionReadMapper professionReadMapper;

	@Override
	public ProfessionDO get(String id){
		return professionReadMapper.get(id);
	}
	
	@Override
	public List<ProfessionDO> list(Map<String, Object> map){
		return professionReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return professionReadMapper.count(map);
	}
	
	@Override
	public int save(ProfessionDO profession){
		return professionWriteMapper.save(profession);
	}
	
	@Override
	public int update(ProfessionDO profession){
		return professionWriteMapper.update(profession);
	}
	
	@Override
	public int remove(String id){
		return professionWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return professionWriteMapper.batchRemove(ids);
	}

	@Override
	public List<ProfessionDO> listByName(String name) {
		return professionReadMapper.listByName(name);
	}

	@Override
	public List<ProfessionDO> listByNumber(String number) {
		return professionReadMapper.listByNumber(number);
	}

}
