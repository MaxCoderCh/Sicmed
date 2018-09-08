package com.prostate.user.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.user.entity.FeedbackDO;
import com.prostate.user.service.FeedbackService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 用户意见反馈表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
 
@Controller
@RequestMapping("/user/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	@GetMapping()
	@RequiresPermissions("user:feedback:feedback")
	String Feedback(){
	    return "user/feedback/feedback";
	}



	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("user:feedback:feedback")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FeedbackDO> feedbackList = feedbackService.list(query);
		//只是简单的页面列表展示，因此先展示25个字。
		for (FeedbackDO feedbackDO:feedbackList) {
			String str = feedbackDO.getFeedbackText();
			if (str.length()>50){
				str = str.substring(0,50)+"……";
			}
			feedbackDO.setFeedbackText(str);
		}
		int total = feedbackService.count(query);
		PageUtils pageUtils = new PageUtils(feedbackList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("user:feedback:add")
	String add(){
	    return "user/feedback/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("user:feedback:edit")
	String edit(@PathVariable("id") String id,Model model){
		FeedbackDO feedback = feedbackService.get(id);
		model.addAttribute("feedback", feedback);
	    return "user/feedback/edit";
	}


	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/8 15:39
	 *@Description: 跳转反馈详情页
	 *@param:
	*/
	@GetMapping("/details/{id}")
	@RequiresPermissions("user:feedback:details")
	String details(@PathVariable("id") String id,Model model){
		FeedbackDO feedback = feedbackService.get(id);
		model.addAttribute("feedback", feedback);
		return "user/feedback/details";
	}


	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("user:feedback:add")
	public R save( FeedbackDO feedback){
		if(feedbackService.save(feedback)>0){
			return R.ok();
		}
		return R.error();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("user:feedback:edit")
	public R update( FeedbackDO feedback){
		feedbackService.update(feedback);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("user:feedback:remove")
	public R remove( String id){
		if(feedbackService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("user:feedback:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		feedbackService.batchRemove(ids);
		return R.ok();
	}
	
}
