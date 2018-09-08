package com.prostate.system.mapper.master;

import com.prostate.system.domain.DeptDO;
import org.springframework.stereotype.Repository;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Repository
public interface DeptWriteMapper {

	int save(DeptDO dept);
	
	int update(DeptDO dept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

}
