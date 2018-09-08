package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.NihCpsiDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:59 2018/5/21
 */
@Repository
public interface NihCpsiManagerReadMapper {
    NihCpsiDO get(String id);

    List<NihCpsiDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(NihCpsiDO scaleDO);

    int update(NihCpsiDO scaleDO);

    int remove(String id);

    int batchRemove(String[] ids);

    List<NihCpsiDO> listByName(String name);

    List<NihCpsiDO> listByNumber(String number);

    public List<NihCpsiDO> getTree(Map<String, Object> map);
}
