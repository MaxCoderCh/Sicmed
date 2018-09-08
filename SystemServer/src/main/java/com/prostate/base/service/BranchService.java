package com.prostate.base.service;

import com.prostate.base.domain.BranchDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 科室表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public interface BranchService {
	
	BranchDO get(String id);


	BranchDO getByName(String deptName);
	
	List<BranchDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BranchDO dept);
	
	int update(BranchDO dept);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	Tree<BranchDO> getTree();

}
