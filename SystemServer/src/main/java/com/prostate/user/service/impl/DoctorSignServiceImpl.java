package com.prostate.user.service.impl;


import com.prostate.user.mapper.slaver.DoctorSingReadMapper;
import com.prostate.user.mapper.master.DoctorSingWriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.prostate.user.entity.DoctorSignDO;
import com.prostate.user.service.DoctorSignService;



@Service
public class DoctorSignServiceImpl implements DoctorSignService {

	@Autowired
	private DoctorSingReadMapper doctorSingReadMapper;

	@Autowired
	private DoctorSingWriteMapper doctorSingWriteMapper;



	@Override
	public DoctorSignDO get(String id){
		return doctorSingReadMapper.get(id);
	}

	@Override
	public List<DoctorSignDO> list(Map<String, Object> map){
		return doctorSingReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return doctorSingReadMapper.count(map);
	}

	@Override
	public int save(DoctorSignDO sign){
		return 0;
	}

	@Override
	public int update(DoctorSignDO sign){
		return doctorSingWriteMapper.update(sign);
	}

	@Override
	public int remove(String id){
		return 0;
	}

	@Override
	public int batchRemove(String[] ids){
		return 0;
	}



}
