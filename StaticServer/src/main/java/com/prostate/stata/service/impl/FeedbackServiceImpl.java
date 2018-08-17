package com.prostate.stata.service.impl;


import com.prostate.stata.entity.FeedbackDO;
import com.prostate.stata.mapper.master.FeedbackWriteMapper;
import com.prostate.stata.mapper.slaver.FeedbackReadMapper;
import com.prostate.stata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FeedbackServiceImpl implements FeedbackService {


	@Autowired
	private FeedbackWriteMapper feedbackWriteMapper;

	@Autowired
	private FeedbackReadMapper feedbackReadMapper;

	@Override
	public FeedbackDO get(String id) {
		return feedbackReadMapper.get(id);
	}

	@Override
	public List<FeedbackDO> list(Map<String, Object> map) {
		return feedbackReadMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return feedbackReadMapper.count(map);
	}

	@Override
	public int update(FeedbackDO feedback) {
		return 0;
	}

	@Override
	public int remove(String id) {
		return 0;
	}

	@Override
	public int batchRemove(String[] ids) {
		return 0;
	}

	@Override
	public int save(FeedbackDO feedback){
		return feedbackWriteMapper.save(feedback);
	}
	

	
}
