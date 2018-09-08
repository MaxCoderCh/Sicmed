package com.prostate.user.mapper.slaver;


import java.util.List;
import java.util.Map;

import com.prostate.user.entity.DoctorSignDO;
import org.springframework.stereotype.Repository;

/**
 * 医生认证信息表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-07 10:26:55
 */
@Repository
public interface DoctorSingReadMapper {

	DoctorSignDO get(String id);
	
	List<DoctorSignDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	

}
