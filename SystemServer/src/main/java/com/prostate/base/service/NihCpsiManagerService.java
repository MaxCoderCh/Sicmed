package com.prostate.base.service;

import com.prostate.base.domain.NihCpsiDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:59 2018/5/21
 */
public interface NihCpsiManagerService {
    NihCpsiDO get(String id);

    List<NihCpsiDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(NihCpsiDO nihCpsiDO);

    int update(NihCpsiDO nihCpsiDO);

    int remove(String id);

    int batchRemove(String[] ids);

    Tree<NihCpsiDO> getTree();

    List<NihCpsiDO> listByName(String name);
}
