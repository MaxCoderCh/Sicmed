package com.prostate.base.service;

import com.prostate.base.domain.DigitalRectalDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 前列腺指检量表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
public interface DigitalRectalService {
	
	DigitalRectalDO get(String id);
	
	List<DigitalRectalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DigitalRectalDO digitalRectal);
	
	int update(DigitalRectalDO digitalRectal);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	Tree<DigitalRectalDO> getTree();
}
