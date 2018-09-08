package com.prostate.pra.controller;

import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.pra.entity.ClickCountDoctorDO;
import com.prostate.pra.service.ClickCountDoctorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生之点击统计
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:11
 */
 
@Controller
@RequestMapping("/pra/clickCountDoctor")
public class ClickCountDoctorController {
	@Autowired
	private ClickCountDoctorService clickCountDoctorService;
	
	@GetMapping()
	@RequiresPermissions("pra:clickCountDoctor:clickCountDoctor")
	String CountDoctor(){
	    return "pra/clickCountDoctor/clickCountDoctor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pra:clickCountDoctor:clickCountDoctor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ClickCountDoctorDO> countDoctorList = clickCountDoctorService.list(query);
		int total = clickCountDoctorService.count(query);
		PageUtils pageUtils = new PageUtils(countDoctorList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("pra:clickCountDoctor:add")
	String add(){
	    return "pra/clickCountDoctor/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pra:clickCountDoctor:edit")
	String edit(@PathVariable("id") String id,Model model){
		ClickCountDoctorDO clickCountDoctor = clickCountDoctorService.get(id);
		model.addAttribute("clickCountDoctor", clickCountDoctor);
	    return "pra/clickCountDoctor/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("pra:clickCountDoctor:add")
	public R save( ClickCountDoctorDO clickCountDoctor){
		if(clickCountDoctorService.save(clickCountDoctor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pra:clickCountDoctor:edit")
	public R update( ClickCountDoctorDO clickCountDoctor){
		clickCountDoctorService.update(clickCountDoctor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pra:clickCountDoctor:remove")
	public R remove( String id){
		if(clickCountDoctorService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pra:clickCountDoctor:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		clickCountDoctorService.batchRemove(ids);
		return R.ok();
	}
	
}
