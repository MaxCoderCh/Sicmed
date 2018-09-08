package com.prostate.base.mapper.master;

import com.prostate.base.domain.UltrasonographyBDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * B超量表
 * @author xiaofeng
 * @email 1992lcg@163.com
 * @date 2018-06-05 09:27:00
 */
@Repository
public interface UltrasonographyBWriteMapper {

	UltrasonographyBDO get(String id);

	UltrasonographyBDO getByNumber(String number);

	UltrasonographyBDO getByName(String name);

	List<UltrasonographyBDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UltrasonographyBDO ultrasonographyBDO);

	int update(UltrasonographyBDO ultrasonographyBDO);

	int remove(String id);

	int batchRemove(String[] ids);

	List<UltrasonographyBDO> listByName(String name);


	List<UltrasonographyBDO> getTree(Map<String, Object> map);
}
