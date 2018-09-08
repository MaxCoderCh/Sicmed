package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.NihCpsiReadMapper;
import com.prostate.base.mapper.master.NihCpsiWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.NihCpsiDO;
import com.prostate.base.service.NihCpsiService;



@Service
public class NihCpsiServiceImpl implements NihCpsiService {

	@Autowired
	private NihCpsiWriteMapper nihCpsiWriteMapper;

	@Autowired
	private NihCpsiReadMapper nihCpsiReadMapper;
	@Override
	public NihCpsiDO get(String id){
		return nihCpsiReadMapper.get(id);
	}
	
	@Override
	public List<NihCpsiDO> list(Map<String, Object> map){
		return nihCpsiReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return nihCpsiReadMapper.count(map);
	}
	
	@Override
	public int save(NihCpsiDO nihCpsi){
		return nihCpsiWriteMapper.save(nihCpsi);
	}
	
	@Override
	public int update(NihCpsiDO nihCpsi){
		return nihCpsiWriteMapper.update(nihCpsi);
	}
	
	@Override
	public int remove(String id){
		return nihCpsiWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return nihCpsiWriteMapper.batchRemove(ids);
	}
	
}
