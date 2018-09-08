package com.prostate.user.service;

import com.prostate.user.entity.DoctorDetailDO;
import com.prostate.user.entity.FeedbackDO;

import java.util.List;
import java.util.Map;

/**
 * 医生详情服务
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
public interface DoctorDetailsService {
	
	DoctorDetailDO get(String id);

	List<DoctorDetailDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

//
//	int save(DoctorDetailDO feedback);
//
//	int update(DoctorDetailDO feedback);
//
//	int remove(String id);
//
//	int batchRemove(String[] ids);
}
