package com.prostate.pra.service.impl;

import com.prostate.pra.entity.FocusCountDoctorDO;
import com.prostate.pra.mapper.slaver.FocusCountDoctorReadMapper;
import com.prostate.pra.service.FocusCountDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FocusCountDoctorServiceImpl implements FocusCountDoctorService {

	@Autowired
	private FocusCountDoctorReadMapper countDoctorDao;

	@Override
	public int countByDoctorId(String doctorId) {
		return countDoctorDao.countByDoctorId(doctorId);
	}


	@Override
	public int[] countThisWeek(String doctorId) {
		int[] ints = new int[7];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 6;i>=0;i--){
			int sum = countDoctorDao.countEverDay(doctorId,i);
			ints[6-i] = sum;
		}
		return  ints;
	}


	@Override
	public int[] countThisYear(String doctorId) {

		int[] ints = new int[12];
		//根据医生id循环查询近一年的统计
		for (int i = 11;i>=0;i--){
			int sum = countDoctorDao.countThisYear(doctorId,i);
			ints[11-i] = sum;
		}
		return  ints;
	}


	@Override
	public int[] countThisMooth(String doctorId) {
		int[] ints = new int[31];
		//根据医生id循环查询近七天的问诊统计
		for (int i = 30;i>=0;i--){
			int sum = countDoctorDao.countEverDay(doctorId,i);
			ints[30-i] = sum;
		}
		return  ints;
	}


	@Override
	public FocusCountDoctorDO get(String id){
		return countDoctorDao.get(id);
	}
	
	@Override
	public List<FocusCountDoctorDO> list(Map<String, Object> map){
		return countDoctorDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return countDoctorDao.count(map);
	}
	
	@Override
	public int save(FocusCountDoctorDO countDoctor){
		return countDoctorDao.save(countDoctor);
	}
	
	@Override
	public int update(FocusCountDoctorDO countDoctor){
		return countDoctorDao.update(countDoctor);
	}
	
	@Override
	public int remove(String id){
		return countDoctorDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return countDoctorDao.batchRemove(ids);
	}
	
}
