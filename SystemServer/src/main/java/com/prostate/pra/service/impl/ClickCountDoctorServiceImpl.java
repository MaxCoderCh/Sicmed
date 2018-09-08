package com.prostate.pra.service.impl;

import com.prostate.pra.entity.ClickCountDoctorDO;
import com.prostate.pra.mapper.slaver.ClickCountDoctorReadMApper;
import com.prostate.pra.service.ClickCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ClickCountDoctorServiceImpl implements ClickCountDoctorService {




	@Autowired
	private ClickCountDoctorReadMApper clickCountDoctorReadMApper;

	@Override
	public List<ClickCountDoctorDO> list(Map<String, Object> map) {
		return null;
	}


	@Override
	public ClickCountDoctorDO get(String id){
		return clickCountDoctorReadMApper.get(id);
	}

	@Override
	public int countByDoctorId(String doctorId) {
		return clickCountDoctorReadMApper.countByDoctorId(doctorId);
	}

	@Override
	public int[] countThisWeek(String doctorId) {
		int[] ints = new int[7];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 6;i>=0;i--){
			int sum = clickCountDoctorReadMApper.countEverDay(doctorId,i);
			ints[6-i] = sum;
		}
		return  ints;
	}

	@Override
	public int[] countThisYear(String doctorId) {

		int[] ints = new int[12];
		//根据医生id循环查询近一年的统计
		for (int i = 11;i>=0;i--){
			int sum = clickCountDoctorReadMApper.countThisYear(doctorId,i);
			ints[11-i] = sum;
		}
		return  ints;
	}

	@Override
	public int[] countThisMooth(String doctorId) {
		int[] ints = new int[31];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 30;i>=0;i--){
			int sum = clickCountDoctorReadMApper.countEverDay(doctorId,i);
			ints[30-i] = sum;
		}
		return  ints;
	}


	@Override
	public int count(Map<String, Object> map){
		return clickCountDoctorReadMApper.count(map);
	}
	
	@Override
	public int save(ClickCountDoctorDO countDoctor){
		return clickCountDoctorReadMApper.save(countDoctor);
	}
	
	@Override
	public int update(ClickCountDoctorDO countDoctor){
		return clickCountDoctorReadMApper.update(countDoctor);
	}
	
	@Override
	public int remove(String id){
		return clickCountDoctorReadMApper.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return clickCountDoctorReadMApper.batchRemove(ids);
	}
	
}
