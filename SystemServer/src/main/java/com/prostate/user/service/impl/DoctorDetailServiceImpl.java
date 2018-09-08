package com.prostate.user.service.impl;

import com.prostate.user.entity.DoctorDetailDO;
import com.prostate.user.mapper.slaver.DoctorDetailsReadMapper;
import com.prostate.user.service.DoctorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DoctorDetailServiceImpl implements DoctorDetailsService {


	@Autowired
	private DoctorDetailsReadMapper doctorDetailsReadMapper;
	
	@Override
	public DoctorDetailDO get(String id){
		return doctorDetailsReadMapper.get(id);
	}
	
	@Override
	public List<DoctorDetailDO> list(Map<String, Object> map){
		return doctorDetailsReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return doctorDetailsReadMapper.count(map);
	}
	
//	@Override
//	public int save(DoctorDetailDO detail){
//		return doctorDetailsReadMapper.save(detail);
//	}
//
//	@Override
//	public int update(DoctorDetailDO detail){
//		return doctorDetailsReadMapper.update(detail);
//	}
//
//	@Override
//	public int remove(String id){
//		return doctorDetailsReadMapper.remove(id);
//	}
//
//	@Override
//	public int batchRemove(String[] ids){
//		return doctorDetailsReadMapper.batchRemove(ids);
//	}
	
}
