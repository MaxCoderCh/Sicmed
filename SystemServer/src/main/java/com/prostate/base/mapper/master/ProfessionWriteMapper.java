package com.prostate.base.mapper.master;

import com.prostate.base.domain.ProfessionDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:36
 */
@Repository
public interface ProfessionWriteMapper {

	ProfessionDO get(String id);
	
	List<ProfessionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProfessionDO profession);
	
	int update(ProfessionDO profession);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

	List<ProfessionDO> listByName(String name);

	List<ProfessionDO> listByNumber(String number);
}
