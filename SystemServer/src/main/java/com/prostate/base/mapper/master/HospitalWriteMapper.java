package com.prostate.base.mapper.master;

import com.prostate.base.domain.HospitalDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 医院表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-10 17:53:04
 */
@Repository
public interface HospitalWriteMapper {

	HospitalDO get(String id);

	HospitalDO getByName(String hospitalName);

	HospitalDO getByNumber(String hospitalNumber);
	
	List<HospitalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HospitalDO hospital);
	
	int update(HospitalDO hospital);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
