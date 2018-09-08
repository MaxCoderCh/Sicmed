package com.prostate.base.mapper.master;

import com.prostate.base.domain.PriceDO;
import org.springframework.stereotype.Repository;

/**
 * 价格标签
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
@Repository
public interface PriceWriteMapper {



	int save(PriceDO inquiry);
	
	int update(PriceDO inquiry);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
