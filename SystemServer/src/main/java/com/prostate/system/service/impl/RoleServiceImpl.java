package com.prostate.system.service.impl;

import com.prostate.system.mapper.slaver.RoleMenuReadMapper;
import com.prostate.system.mapper.slaver.RoleReadMapper;
import com.prostate.system.mapper.slaver.UserReadMapper;
import com.prostate.system.mapper.slaver.UserRoleReadMapper;
import com.prostate.system.mapper.master.RoleMenuWriteMapper;
import com.prostate.system.mapper.master.RoleWriteMapper;
import com.prostate.system.mapper.master.UserRoleWriteMapper;
import com.prostate.system.mapper.master.UserWriteMapper;
import com.prostate.system.domain.RoleDO;
import com.prostate.system.domain.RoleMenuDO;
import com.prostate.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";


    @Autowired
    private RoleWriteMapper roleWriteMapper;

    @Autowired
    private RoleReadMapper roleReadMapper;

    @Autowired
    private RoleMenuWriteMapper roleMenuWriteMapper;

    @Autowired
    private RoleMenuReadMapper roleMenuReadMapper;

    @Autowired
    private UserWriteMapper userWriteMapper;

    @Autowired
    private UserReadMapper userReadMapper;

    @Autowired
    private UserRoleWriteMapper userRoleWriteMapper;

    @Autowired
    private UserRoleReadMapper userRoleReadMapper;

    @Override
    public List<RoleDO> list() {
        List<RoleDO> roles = roleReadMapper.list(new HashMap<>(16));
        return roles;
    }


    @Override
    public List<RoleDO> list(Long userId) {
        List<Long> rolesIds = userRoleReadMapper.listRoleId(userId);
        List<RoleDO> roles = roleReadMapper.list(new HashMap<>(16));
        for (RoleDO roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public int save(RoleDO role) {
        int count = roleWriteMapper.save(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuWriteMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuWriteMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = roleWriteMapper.remove(id);
        userRoleWriteMapper.removeByRoleId(id);
        roleMenuWriteMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public RoleDO get(Long id) {
        RoleDO roleDO = roleReadMapper.get(id);
        return roleDO;
    }

    @Override
    public int update(RoleDO role) {
        int r = roleWriteMapper.update(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
        roleMenuWriteMapper.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuWriteMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = roleWriteMapper.batchRemove(ids);
        return r;
    }

}
