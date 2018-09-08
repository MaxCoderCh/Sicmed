package com.prostate.user.mapper.master;

import com.prostate.user.entity.FeedbackDO;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 用户意见反馈表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
@Repository
public interface FeedbackWriteMapper {

	FeedbackDO get(String id);
	
	List<FeedbackDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FeedbackDO feedback);
	
	int update(FeedbackDO feedback);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
