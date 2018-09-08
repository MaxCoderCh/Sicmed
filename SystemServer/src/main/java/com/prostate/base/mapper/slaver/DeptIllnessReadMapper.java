package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.DeptIllnessDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 科室疾病中间表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
@Repository
public interface DeptIllnessReadMapper {

	DeptIllnessDO get(String id);
	
	List<DeptIllnessDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptIllnessDO deptIllness);
	
	int update(DeptIllnessDO deptIllness);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
