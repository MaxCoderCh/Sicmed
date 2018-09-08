package com.prostate.base.service.impl;

import com.prostate.base.mapper.slaver.HospitalDeptReadMapper;
import com.prostate.base.mapper.master.HospitalDeptWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.HospitalDeptDO;
import com.prostate.base.service.HospitalDeptService;



@Service
public class HospitalDeptServiceImpl implements HospitalDeptService {

	@Autowired
	private HospitalDeptWriteMapper hospitalDeptWriteMapper;

	@Autowired
	private HospitalDeptReadMapper hospitalDeptReadMapper;
	
	@Override
	public HospitalDeptDO get(String id){
		return hospitalDeptReadMapper.get(id);
	}
	
	@Override
	public List<HospitalDeptDO> list(Map<String, Object> map){
		return hospitalDeptReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return hospitalDeptReadMapper.count(map);
	}
	
	@Override
	public int save(HospitalDeptDO hospitalDept){
		return hospitalDeptWriteMapper.save(hospitalDept);
	}
	
	@Override
	public int update(HospitalDeptDO hospitalDept){
		return hospitalDeptWriteMapper.update(hospitalDept);
	}
	
	@Override
	public int remove(String id){
		return hospitalDeptWriteMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return hospitalDeptWriteMapper.batchRemove(ids);
	}
	
}
