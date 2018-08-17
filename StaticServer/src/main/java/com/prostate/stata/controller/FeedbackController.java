package com.prostate.stata.controller;


import com.prostate.stata.entity.FeedbackDO;
import com.prostate.stata.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 *@Author:      ykbian
 *@date_time:   2018/8/9 14:28
 *@Description:  用户意见反馈
 *@param:
*/

@RestController
@RequestMapping(value = "/feedback")
public class FeedbackController extends BaseController {


	@Autowired
	private FeedbackService feedbackService;

	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/9 14:36
	 *@Description:  用户添加反馈意见
	 *@param:   用户token信息，用户添加的反馈意见。
	*/
	@PostMapping(value = "/add")
	public Map add(String feedbackText) {
		//反馈意见信息太长的话会爆表，直接返回错误信息。
		if (feedbackText.length() > 1000 ){
			return insertFailedResponse();
		}
		FeedbackDO feedbackDO = new FeedbackDO();
		feedbackDO.setCreateUser(getToken());
		feedbackDO.setFeedbackText(feedbackText);
		feedbackDO.setCreateTime(new Date());
		int result = feedbackService.save(feedbackDO);
		if (result > 0) {
			return insertSuccseeResponse(feedbackDO);
		}
		return insertFailedResponse();

	}



}
