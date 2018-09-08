package com.prostate.base.service;

import com.prostate.base.domain.InquiryDO;
import com.prostate.base.domain.PriceDO;

import java.util.List;
import java.util.Map;

/**
 * 价格标签服务
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
public interface PriceService {
	
	PriceDO get(String id);
	
	List<PriceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PriceDO priceDO);
	
	int update(PriceDO priceDO);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
