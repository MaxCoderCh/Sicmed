package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.PriceDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 价格标签
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
@Repository
public interface PriceReadMapper {

	PriceDO get(String id);
	
	List<PriceDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	

}
