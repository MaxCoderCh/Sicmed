package com.prostate.system.mapper.master;

import com.prostate.system.domain.RoleMenuDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Repository
public interface RoleMenuWriteMapper {

	int count(Map<String, Object> map);
	
	int save(RoleMenuDO roleMenu);
	
	int update(RoleMenuDO roleMenu);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int removeByRoleId(Long roleId);

	int removeByMenuId(Long menuId);
	
	int batchSave(List<RoleMenuDO> list);
}
