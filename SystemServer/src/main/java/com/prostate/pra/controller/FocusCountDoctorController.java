package com.prostate.pra.controller;

import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.pra.entity.FocusCountDoctorDO;
import com.prostate.pra.service.FocusCountDoctorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生关注统计表
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:17
 */
 
@Controller
@RequestMapping("/pra/focusCountDoctor")
public class FocusCountDoctorController {
	@Autowired
	private FocusCountDoctorService focusCountDoctorService;
	
	@GetMapping()
	@RequiresPermissions("pra:focusCountDoctor:focusCountDoctor")
	String CountDoctor(){
	    return "pra/focusCountDoctor/focusCountDoctor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pra:focusCountDoctor:focusCountDoctor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<FocusCountDoctorDO> countDoctorList = focusCountDoctorService.list(query);
		int total = focusCountDoctorService.count(query);
		PageUtils pageUtils = new PageUtils(countDoctorList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("pra:focusCountDoctor:add")
	String add(){
	    return "pra/focusCountDoctor/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pra:focusCountDoctor:edit")
	String edit(@PathVariable("id") String id,Model model){
		FocusCountDoctorDO focusCountDoctor = focusCountDoctorService.get(id);
		model.addAttribute("focusCountDoctor", focusCountDoctor);
	    return "pra/focusCountDoctor/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("pra:focusCountDoctor:add")
	public R save( FocusCountDoctorDO focusCountDoctor){
		if(focusCountDoctorService.save(focusCountDoctor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pra:focusCountDoctor:edit")
	public R update( FocusCountDoctorDO focusCountDoctor){
		focusCountDoctorService.update(focusCountDoctor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pra:focusCountDoctor:remove")
	public R remove( String id){
		if(focusCountDoctorService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pra:focusCountDoctor:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		focusCountDoctorService.batchRemove(ids);
		return R.ok();
	}
	
}
