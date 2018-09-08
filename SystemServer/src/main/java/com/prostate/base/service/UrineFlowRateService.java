package com.prostate.base.service;

import com.prostate.base.domain.UrineFlowRateDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * 尿流率量表
 * 
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
public interface UrineFlowRateService {

	UrineFlowRateDO get(String id);

	List<UrineFlowRateDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(UrineFlowRateDO urineRoutineDO);

	int update(UrineFlowRateDO urineRoutineDO);

	int remove(String id);

	int batchRemove(String[] ids);

	String[] listParentId();

	//int getDeptUserNumber(String id);

	Tree<UrineFlowRateDO> getTree();
}
