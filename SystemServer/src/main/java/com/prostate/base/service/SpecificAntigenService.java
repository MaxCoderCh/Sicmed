package com.prostate.base.service;

import com.prostate.base.domain.SpecificAntigenDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 特异性抗原量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
public interface SpecificAntigenService {
	
	SpecificAntigenDO get(String id);

	Tree<SpecificAntigenDO> getTree();
	
	List<SpecificAntigenDO> list(Map<String, Object> map);


	List<SpecificAntigenDO> getByParentId(String id);

	int count(Map<String, Object> map);
	
	int save(SpecificAntigenDO specificAntigen);
	
	int update(SpecificAntigenDO specificAntigen);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
