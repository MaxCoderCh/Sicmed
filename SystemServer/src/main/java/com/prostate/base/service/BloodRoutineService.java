package com.prostate.base.service;

import com.prostate.base.domain.BloodRoutineDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 血常规量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 10:28:07
 */
public interface BloodRoutineService {


	Tree<BloodRoutineDO> getTree();


	BloodRoutineDO getByNumber(String deptNumber);

	BloodRoutineDO getByName(String deptName);

	BloodRoutineDO get(String id);
	
	List<BloodRoutineDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BloodRoutineDO bloodRoutine);
	
	int update(BloodRoutineDO bloodRoutine);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
