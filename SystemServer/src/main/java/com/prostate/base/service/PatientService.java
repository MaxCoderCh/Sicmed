package com.prostate.base.service;


import com.prostate.base.domain.PatientDO;

import java.util.List;
import java.util.Map;

/**
 * 患者标签
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 12:00:59
 */
public interface PatientService {
	
	PatientDO get(String id);
	
	List<PatientDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PatientDO patient);
	
	int update(PatientDO patient);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
