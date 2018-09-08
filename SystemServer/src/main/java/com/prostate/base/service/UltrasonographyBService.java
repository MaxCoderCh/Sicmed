package com.prostate.base.service;

import com.prostate.base.domain.UltrasonographyBDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * B超量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
public interface UltrasonographyBService {
	
	UltrasonographyBDO get(String id);

	List<UltrasonographyBDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(UltrasonographyBDO ultrasonographyBDO);

	int update(UltrasonographyBDO ultrasonographyBDO);

	int remove(String id);

	int batchRemove(String[] ids);

	String[] listParentId();

	//int getDeptUserNumber(String id);

	Tree<UltrasonographyBDO> getTree();
}
