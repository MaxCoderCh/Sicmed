package com.prostate.base.mapper.master;

import com.prostate.base.domain.ScaleDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:29 2018/5/14
 */
@Repository
public interface ScaleManagerWriteMapper {

    ScaleDO get(String id);

    List<ScaleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(ScaleDO scaleDO);

    int update(ScaleDO scaleDO);

    int remove(String id);

    int batchRemove(String[] ids);

    List<ScaleDO> listByName(String name);

    List<ScaleDO> listByNumber(String number);

    public List<ScaleDO> getTree(Map<String, Object> map);
}
