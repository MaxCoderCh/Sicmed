package com.prostate.user.mapper.slaver;


import java.util.List;
import java.util.Map;

import com.prostate.user.entity.DoctorDetailDO;
import org.springframework.stereotype.Repository;

/**
 * 医生个人信息表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-13 18:04:59
 */
@Repository
public interface DoctorDetailsReadMapper {

	DoctorDetailDO get(String id);
	
	List<DoctorDetailDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	

}
