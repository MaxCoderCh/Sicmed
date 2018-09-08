package com.prostate.system.mapper.slaver;

import com.prostate.system.domain.DeptDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Repository
public interface DeptReadMapper {

	DeptDO get(Long deptId);
	
	List<DeptDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	Long[] listParentDept();
	
	int getDeptUserNumber(Long deptId);
}
