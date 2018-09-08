package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.InquiryDO;
import com.prostate.base.service.InquiryService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 会诊类型标签
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 13:09:03
 */
 
@Controller
@RequestMapping("/base/inquiry")
public class InquiryController {








	@Autowired
	private InquiryService inquiryService;
	
	@GetMapping()
	@RequiresPermissions("base:inquiry:inquiry")
	String Inquiry(){
	    return "base/inquiry/inquiry";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:inquiry:inquiry")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InquiryDO> inquiryList = inquiryService.list(query);
		int total = inquiryService.count(query);
		PageUtils pageUtils = new PageUtils(inquiryList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:inquiry:add")
	String add(){
	    return "base/inquiry/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:inquiry:edit")
	String edit(@PathVariable("id") String id,Model model){
		InquiryDO inquiry = inquiryService.get(id);
		model.addAttribute("inquiry", inquiry);
	    return "base/inquiry/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:inquiry:add")
	public R save( InquiryDO inquiry){
		inquiry.setCreateTime(new Date());
//		inquiry.setCreateUser(ShiroUtils.getUserId().toString());
		if(inquiryService.save(inquiry)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:inquiry:edit")
	public R update( InquiryDO inquiry){
		inquiryService.update(inquiry);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:inquiry:remove")
	public R remove( String id){
		if(inquiryService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:inquiry:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		inquiryService.batchRemove(ids);
		return R.ok();
	}
	
}
