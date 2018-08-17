package com.prostate.stata.mapper.slaver;

import com.prostate.stata.entity.VersionInformationDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 版本信息
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
@Repository
public interface VersionInformationReadMapper {

	VersionInformationDO get(String id);


	VersionInformationDO getByVersionNumber(String versionNumber);


	List<VersionInformationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	

}
