package com.prostate.base.service;

import com.prostate.base.domain.DeptIllnessDO;

import java.util.List;
import java.util.Map;

/**
 * 科室疾病中间表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public interface DeptIllnessService {
	
	DeptIllnessDO get(String id);
	
	List<DeptIllnessDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptIllnessDO deptIllness);
	
	int update(DeptIllnessDO deptIllness);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
