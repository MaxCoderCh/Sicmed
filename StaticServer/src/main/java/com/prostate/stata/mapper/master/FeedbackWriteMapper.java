package com.prostate.stata.mapper.master;

import com.prostate.stata.entity.FeedbackDO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户意见反馈表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
@Repository
public interface FeedbackWriteMapper {


	
	int save(FeedbackDO feedback);
	
	int update(FeedbackDO feedback);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
