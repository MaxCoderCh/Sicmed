package com.prostate.stata.mapper.master;

import com.prostate.stata.entity.VersionInformationDO;
import org.springframework.stereotype.Repository;

/**
 * 版本信息
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
@Repository
public interface VersionInformationWriteMapper {


	
	int save(VersionInformationDO information);
	
	int update(VersionInformationDO information);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
