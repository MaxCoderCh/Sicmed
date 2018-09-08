package com.prostate.base.service;

import com.prostate.base.domain.CityDO;
import com.prostate.base.domain.ScaleDO;
import com.prostate.common.domain.Tree;

import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 10:45 2018/5/14
 */
public interface ScaleManagerService {

    ScaleDO get(String id);

    List<ScaleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ScaleDO scaleDO);

    int update(ScaleDO scaleDO);

    int remove(String id);

    int batchRemove(String[] ids);

    Tree<ScaleDO> getTree();

    List<ScaleDO> listByName(String name);


}
