package com.prostate.system.mapper.master;

import com.prostate.system.domain.MenuDO;
import org.springframework.stereotype.Repository;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Repository
public interface MenuWriteMapper {

	int save(MenuDO menu);
	
	int update(MenuDO menu);
	
	int remove(Long menuId);
	
	int batchRemove(Long[] menuIds);
	
}
