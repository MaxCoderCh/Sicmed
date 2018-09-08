package com.prostate.system.mapper.master;

import com.prostate.system.domain.UserDO;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Repository
public interface UserWriteMapper {


	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
}
