package com.prostate.pra.service.impl;


import com.prostate.pra.entity.InquriyCountDoctorDO;
import com.prostate.pra.mapper.slaver.InquriyCountDoctorReadMapper;
import com.prostate.pra.service.InquriyCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class InquriyCountDoctorServiceImpl implements InquriyCountDoctorService {


	@Autowired
	private InquriyCountDoctorReadMapper inquriyCountDoctorReadMapper;

	@Override
	public int countByDoctorId(String doctorId) {
		return inquriyCountDoctorReadMapper.countByDoctorId(doctorId);
	}


	@Override
	public int[] countThisWeek(String doctorId) {
		int[] ints = new int[7];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 6;i>=0;i--){
			int sum = inquriyCountDoctorReadMapper.countEverDay(doctorId,i);
			ints[6-i] = sum;
		}
		return  ints;
	}

	@Override
	public int[] countThisMooth(String doctorId) {
		int[] ints = new int[31];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 30;i>=0;i--){
			int sum = inquriyCountDoctorReadMapper.countEverDay(doctorId,i);
			ints[30-i] = sum;
		}
		return  ints;
	}


	@Override
	public int[] countThisYear(String doctorId) {

		int[] ints = new int[12];
		//根据医生id循环查询近一年的统计
		for (int i = 11;i>=0;i--){
			int sum = inquriyCountDoctorReadMapper.countThisYear(doctorId,i);
			ints[11-i] = sum;
		}
		return  ints;
	}



	@Override
	public InquriyCountDoctorDO get(String id){
		return inquriyCountDoctorReadMapper.get(id);
	}
	
	@Override
	public List<InquriyCountDoctorDO> list(Map<String, Object> map){
		return inquriyCountDoctorReadMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return inquriyCountDoctorReadMapper.count(map);
	}
	
	@Override
	public int save(InquriyCountDoctorDO countDoctor){
		return inquriyCountDoctorReadMapper.save(countDoctor);
	}
	
	@Override
	public int update(InquriyCountDoctorDO countDoctor){
		return inquriyCountDoctorReadMapper.update(countDoctor);
	}
	
	@Override
	public int remove(String id){
		return inquriyCountDoctorReadMapper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return inquriyCountDoctorReadMapper.batchRemove(ids);
	}
	
}
