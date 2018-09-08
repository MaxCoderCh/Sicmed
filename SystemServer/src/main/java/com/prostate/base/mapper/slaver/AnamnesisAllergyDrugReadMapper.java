package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.AnamnesisAllergyDrugDO;
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
public interface AnamnesisAllergyDrugReadMapper {

	AnamnesisAllergyDrugDO get(String id);
	
	List<AnamnesisAllergyDrugDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AnamnesisAllergyDrugDO anamnesisAllergyDrug);
	
	int update(AnamnesisAllergyDrugDO anamnesisAllergyDrug);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
