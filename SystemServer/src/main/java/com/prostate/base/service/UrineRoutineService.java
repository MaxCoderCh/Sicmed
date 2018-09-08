package com.prostate.base.service;

import com.prostate.base.domain.UrineRoutineDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 尿常规量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-04 10:40:25
 */
public interface UrineRoutineService {
	UrineRoutineDO get(String id);

	List<UrineRoutineDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(UrineRoutineDO urineRoutineDO);

	int update(UrineRoutineDO urineRoutineDO);

	int remove(String id);

	int batchRemove(String[] ids);

	String[] listParentId();

	int getDeptUserNumber(String id);

	Tree<UrineRoutineDO> getTree();
}
