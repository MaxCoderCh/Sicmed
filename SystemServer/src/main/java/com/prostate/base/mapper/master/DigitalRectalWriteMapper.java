package com.prostate.base.mapper.master;

import com.prostate.base.domain.DigitalRectalDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 前列腺指检量表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-06-04 15:18:12
 */
@Repository
public interface DigitalRectalWriteMapper {

	DigitalRectalDO get(String id);
	
	List<DigitalRectalDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DigitalRectalDO digitalRectal);
	
	int update(DigitalRectalDO digitalRectal);
	
	int remove(String id);
	
	int batchRemove(String[] ids);



	List<DigitalRectalDO> getTree(Map<String, Object> map);
}
