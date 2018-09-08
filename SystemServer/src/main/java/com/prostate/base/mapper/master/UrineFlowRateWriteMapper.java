package com.prostate.base.mapper.master;

import com.prostate.base.domain.UrineFlowRateDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 尿流率量表
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
@Repository
public interface UrineFlowRateWriteMapper {

	UrineFlowRateDO get(String id);

	UrineFlowRateDO getByNumber(String number);

	UrineFlowRateDO getByName(String name);

	List<UrineFlowRateDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UrineFlowRateDO urineFlowRateDO);

	int update(UrineFlowRateDO urineFlowRateDO);

	int remove(String id);

	int batchRemove(String[] ids);

	List<UrineFlowRateDO> listByName(String name);


	List<UrineFlowRateDO> getTree(Map<String, Object> map);
}
