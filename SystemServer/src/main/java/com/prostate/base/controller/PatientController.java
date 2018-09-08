package com.prostate.base.controller;

import java.util.List;
import java.util.Map;

import com.prostate.base.domain.PatientDO;
import com.prostate.base.service.PatientService;
import com.prostate.common.utils.ShiroUtils;
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

import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 患者标签
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-07-26 12:00:59
 */
 
@Controller
@RequestMapping("/base/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;
	
	@GetMapping()
	@RequiresPermissions("base:patient:patient")
	String Patient(){
	    return "base/patient/patient";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:patient:patient")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PatientDO> patientList = patientService.list(query);
		if (!patientList.isEmpty()){
			for (PatientDO patientDO:patientList) {
				if (patientDO.getCreateUser()==null||patientDO.getCreateUser().equals("")){
					patientDO.setCreateUser("后台创建");
				}
			}
		}
		int total = patientService.count(query);
		PageUtils pageUtils = new PageUtils(patientList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:patient:add")
	String add(){
	    return "base/patient/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:patient:edit")
	String edit(@PathVariable("id") String id,Model model){
		PatientDO patient = patientService.get(id);
		model.addAttribute("patient", patient);
	    return "base/patient/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:patient:add")
	public R save( PatientDO patient){
		//patient.setCreateUser(ShiroUtils.getUserId().toString());
		if(patientService.save(patient)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:patient:edit")
	public R update( PatientDO patient){
		patientService.update(patient);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:patient:remove")
	public R remove( String id){
		if(patientService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:patient:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		patientService.batchRemove(ids);
		return R.ok();
	}
	
}
