package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.ScaleReadMapper;
import com.prostate.base.mapper.master.ScaleWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.ScaleDO;
import com.prostate.base.service.ScaleService;



@Service
public class ScaleServiceImpl implements ScaleService {

	@Autowired
	private ScaleWriteMapper scaleWriteMapper;


	@Autowired
	private ScaleReadMapper scaleReadMapper;
	@Override
	public ScaleDO get(String id){
		return scaleReadMapper.get(id);
	}
	
	@Override
	public List<ScaleDO> list(Map<String, Object> map){
		return scaleReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return scaleReadMapper.count(map);
	}
	
	@Override
	public int save(ScaleDO scale){
		return scaleWriteMapper.save(scale);
	}
	
	@Override
	public int update(ScaleDO scale){
		return scaleWriteMapper.update(scale);
	}
	
	@Override
	public int remove(String id){
		return scaleWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return scaleWriteMapper.batchRemove(ids);
	}
	
}
