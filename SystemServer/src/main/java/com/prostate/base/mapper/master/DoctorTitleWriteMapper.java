package com.prostate.base.mapper.master;

import com.prostate.base.domain.DoctorTitleDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
@Repository
public interface DoctorTitleWriteMapper {

	DoctorTitleDO get(String id);

	DoctorTitleDO getByName(String doctorTitleName);

	DoctorTitleDO getByNumber(String doctorTitleNumber);
	
	List<DoctorTitleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DoctorTitleDO doctorTitle);
	
	int update(DoctorTitleDO doctorTitle);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
