package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.UrineRoutineDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 尿常规量表
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-04 10:40:25
 */
@Repository
public interface UrineRoutineReadMapper {

	UrineRoutineDO get(String id);

	UrineRoutineDO getByNumber(String deptNumber);

	UrineRoutineDO getByName(String deptName);

	List<UrineRoutineDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UrineRoutineDO dept);

	int update(UrineRoutineDO dept);

	int remove(String id);

	int batchRemove(String[] ids);

	List<UrineRoutineDO> listByName(String name);


	List<UrineRoutineDO> getTree(Map<String, Object> map);
}
