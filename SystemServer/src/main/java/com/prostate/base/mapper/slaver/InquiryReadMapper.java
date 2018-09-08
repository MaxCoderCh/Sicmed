package com.prostate.base.mapper.slaver;

import com.prostate.base.domain.InquiryDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 会诊类型标签
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
@Repository
public interface InquiryReadMapper {

	InquiryDO get(String id);
	
	List<InquiryDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(InquiryDO inquiry);
	
	int update(InquiryDO inquiry);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
