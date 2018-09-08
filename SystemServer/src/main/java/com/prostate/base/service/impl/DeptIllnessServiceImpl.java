package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.DeptIllnessReadMapper;
import com.prostate.base.mapper.master.DeptIllnessWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.DeptIllnessDO;
import com.prostate.base.service.DeptIllnessService;



@Service
public class DeptIllnessServiceImpl implements DeptIllnessService {

	@Autowired
	private DeptIllnessWriteMapper deptIllnessWriteMapper;


	@Autowired
	private DeptIllnessReadMapper deptIllnessReadMapper;
	
	@Override
	public DeptIllnessDO get(String id){
		return deptIllnessReadMapper.get(id);
	}
	
	@Override
	public List<DeptIllnessDO> list(Map<String, Object> map){
		return deptIllnessReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deptIllnessReadMapper.count(map);
	}
	
	@Override
	public int save(DeptIllnessDO deptIllness){
		return deptIllnessWriteMapper.save(deptIllness);
	}
	
	@Override
	public int update(DeptIllnessDO deptIllness){
		return deptIllnessWriteMapper.update(deptIllness);
	}
	
	@Override
	public int remove(String id){
		return deptIllnessWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return deptIllnessWriteMapper.batchRemove(ids);
	}
	
}
