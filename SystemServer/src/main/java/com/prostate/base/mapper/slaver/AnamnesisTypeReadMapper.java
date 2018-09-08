package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.AnamnesisTypeDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author xiaobai
 * @email 1992lcg@163.com
 * @date 2018-05-04 14:46:36
 */
@Repository
public interface AnamnesisTypeReadMapper {

	AnamnesisTypeDO get(String id);
	
	List<AnamnesisTypeDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(AnamnesisTypeDO anamnesisType);
	
	int update(AnamnesisTypeDO anamnesisType);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<AnamnesisTypeDO> listByName(String name);

	List<AnamnesisTypeDO> listByNumber(String number);
}
