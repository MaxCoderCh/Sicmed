package com.prostate.pra.controller;

import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.pra.entity.InquriyCountDoctorDO;
import com.prostate.pra.service.InquriyCountDoctorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生之问诊统计
 * 
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:24
 */
 
@Controller
@RequestMapping("/pra/inquriyCountDoctor")
public class InquriyCountDoctorController {
	@Autowired
	private InquriyCountDoctorService countDoctorService;
	
	@GetMapping()
	@RequiresPermissions("pra:inquriyCountDoctor:inquriyCountDoctor")
	String CountDoctor(){
	    return "pra/inquriyCountDoctor/inquriyCountDoctor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pra:inquriyCountDoctor:inquriyCountDoctor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InquriyCountDoctorDO> countDoctorList = countDoctorService.list(query);
		int total = countDoctorService.count(query);
		PageUtils pageUtils = new PageUtils(countDoctorList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("pra:inquriyCountDoctor:add")
	String add(){
	    return "pra/inquriyCountDoctor/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pra:inquriyCountDoctor:edit")
	String edit(@PathVariable("id") String id,Model model){
		InquriyCountDoctorDO inquriyCountDoctor = countDoctorService.get(id);
		model.addAttribute("inquriyCountDoctor", inquriyCountDoctor);
	    return "pra/inquriyCountDoctor/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("pra:inquriyCountDoctor:add")
	public R save( InquriyCountDoctorDO inquriyCountDoctor){
		if(countDoctorService.save(inquriyCountDoctor)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pra:inquriyCountDoctor:edit")
	public R update( InquriyCountDoctorDO inquriyCountDoctor){
		countDoctorService.update(inquriyCountDoctor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pra:inquriyCountDoctor:remove")
	public R remove( String id){
		if(countDoctorService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pra:inquriyCountDoctor:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		countDoctorService.batchRemove(ids);
		return R.ok();
	}
	
}
