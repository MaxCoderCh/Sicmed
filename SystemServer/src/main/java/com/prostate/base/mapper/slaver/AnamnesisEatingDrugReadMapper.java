package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.AnamnesisEatingDrugDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
@Repository
public interface AnamnesisEatingDrugReadMapper {

	AnamnesisEatingDrugDO get(String id);

	AnamnesisEatingDrugDO getByName(String eatingDrugName);


	AnamnesisEatingDrugDO getByNumber(String eatingDrugNumber);
	
	List<AnamnesisEatingDrugDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AnamnesisEatingDrugDO anamnesisEatingDrug);
	
	int update(AnamnesisEatingDrugDO anamnesisEatingDrug);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
