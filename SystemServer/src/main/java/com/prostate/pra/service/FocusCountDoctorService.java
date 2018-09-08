package com.prostate.pra.service;

import com.prostate.pra.entity.FocusCountDoctorDO;

import java.util.List;
import java.util.Map;

/**
 * 医生关注统计表
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:17
 */
public interface FocusCountDoctorService {
	
	FocusCountDoctorDO get(String id);
	
	List<FocusCountDoctorDO> list(Map<String, Object> map);


	/**
	 * 根据医生id统计数据
	 */
	int countByDoctorId(String doctorId);

	/**
	 * 统计最近一周数据
	 */
	int[]  countThisWeek(String doctorId);

	/**
	 * 统计最近一月数据
	 */
	int[]  countThisMooth(String doctorId);

	/**
	 * 统计一年的数据
	 */
	int[]  countThisYear(String doctorId);


	int count(Map<String, Object> map);
	
	int save(FocusCountDoctorDO countDoctor);
	
	int update(FocusCountDoctorDO countDoctor);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
