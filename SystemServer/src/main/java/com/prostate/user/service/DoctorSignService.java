package com.prostate.user.service;

import com.prostate.user.entity.DoctorSignDO;

import java.util.List;
import java.util.Map;

/**
 * 医生认证信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-07 10:26:55
 */
public interface DoctorSignService {
	
	DoctorSignDO get(String id);
	
	List<DoctorSignDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);



	int save(DoctorSignDO sign);
	
	int update(DoctorSignDO sign);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
