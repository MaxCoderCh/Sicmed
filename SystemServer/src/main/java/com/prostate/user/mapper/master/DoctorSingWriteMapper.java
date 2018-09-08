package com.prostate.user.mapper.master;


import com.prostate.user.entity.DoctorSignDO;
import org.springframework.stereotype.Repository;

/**
 * 医生认证信息表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-07 10:26:55
 */
@Repository
public interface DoctorSingWriteMapper {


	
	int save(DoctorSignDO sign);
	
	int update(DoctorSignDO sign);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
