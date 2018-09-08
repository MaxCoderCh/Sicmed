package com.prostate.base.mapper.master;

import com.prostate.base.domain.CityDO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 城市表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
@Repository
public interface CityWriteMapper {


	
	int count(Map<String, Object> map);
	
	int save(CityDO city);
	
	int update(CityDO city);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
