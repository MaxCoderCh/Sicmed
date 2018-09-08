package com.prostate.base.service;

import com.prostate.base.domain.IpssDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public interface IpssManagerService {
	
	IpssDO get(String id);
	
	List<IpssDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(IpssDO ipss);
	
	int update(IpssDO ipss);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	Tree<IpssDO> getTree();

	List<IpssDO> listByName(String name);
}
