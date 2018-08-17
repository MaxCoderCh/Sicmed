package com.prostate.stata.service;

import com.prostate.stata.entity.FeedbackDO;

import java.util.List;
import java.util.Map;

/**
 * 用户意见反馈表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
public interface FeedbackService {
	
	FeedbackDO get(String id);

	List<FeedbackDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);


	int save(FeedbackDO feedback);
	
	int update(FeedbackDO feedback);

	int remove(String id);

	int batchRemove(String[] ids);
}
