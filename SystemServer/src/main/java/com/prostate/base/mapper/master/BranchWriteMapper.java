package com.prostate.base.mapper.master;

import com.prostate.base.domain.BranchDO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 科室表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
@Repository
public interface BranchWriteMapper {



	int count(Map<String, Object> map);

	int save(BranchDO branch);

	int update(BranchDO branch);

	int remove(String id);

	int batchRemove(String[] ids);


}
