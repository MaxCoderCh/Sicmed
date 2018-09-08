package com.prostate.base.service;

import com.prostate.base.domain.NationDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
public interface NationService {
	
	NationDO get(String id);

	NationDO getByName(String nationName);

	NationDO getByNumber(String nationNumber);
	
	List<NationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(NationDO nation);
	
	int update(NationDO nation);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
//
//	//批量修改
//	int batchUpdate(String[] ids);
}
