package com.prostate.base.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.base.domain.DoctorTitleDO;
import com.prostate.base.service.DoctorTitleService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */
 
@Controller
@RequestMapping("/base/doctorTitle")
public class DoctorTitleController {
	@Autowired
	private DoctorTitleService doctorTitleService;

	@GetMapping()
	@RequiresPermissions("base:doctorTitle:doctorTitle")
	String DoctorTitle(){
	    return "base/doctorTitle/doctorTitle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:doctorTitle:doctorTitle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DoctorTitleDO> doctorTitleList = doctorTitleService.list(query);
		int total = doctorTitleService.count(query);
		PageUtils pageUtils = new PageUtils(doctorTitleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:doctorTitle:add")
	String add(){
	    return "base/doctorTitle/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:doctorTitle:edit")
	String edit(@PathVariable("id") String id,Model model){
		DoctorTitleDO doctorTitle = doctorTitleService.get(id);
		model.addAttribute("doctorTitle", doctorTitle);
	    return "base/doctorTitle/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:doctorTitle:add")
	public R save( @Validated(GroupWithoutID.class)DoctorTitleDO doctorTitle){
		if (doctorTitleService.getByName(doctorTitle.getDoctorTitleName()) == null
				&& doctorTitleService.getByNumber(doctorTitle.getDoctorTitleNumber()) == null){
			doctorTitle.setCreateTime(new Date());
			doctorTitle.setCreateUser(ShiroUtils.getUserId().toString());
			doctorTitle.setDelFlag("0");
			if(doctorTitleService.save(doctorTitle)>0){
				return R.ok();
			}
		}
		return R.error(20001,"名称或者编号重复");
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("base:doctorTitle:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})DoctorTitleDO doctorTitle){
		DoctorTitleDO  doctorTitleDOById = doctorTitleService.get(doctorTitle.getId());
		if (!doctorTitleDOById.getDoctorTitleName().equalsIgnoreCase(doctorTitle.getDoctorTitleName())){
			if (doctorTitleService.getByName(doctorTitle.getDoctorTitleName()) != null){
				return R.error(20001,"名称重复");
			}
		}
		if (!doctorTitleDOById.getDoctorTitleNumber().equalsIgnoreCase(doctorTitle.getDoctorTitleNumber())){
			if (doctorTitleService.getByNumber(doctorTitle.getDoctorTitleNumber()) != null){
				return R.error(20001,"编号重复");
			}
		}
		doctorTitle.setUpdateTime(new Date());
		doctorTitle.setUpdateUser(ShiroUtils.getUserId().toString());
		doctorTitleService.update(doctorTitle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:doctorTitle:remove")
	public R remove( String id){
		DoctorTitleDO doctorTitleDO = doctorTitleService.get(id);
		if (doctorTitleDO == null){
			return R.error(20004,"找不到数据");
		}
		doctorTitleDO.setDelFlag("1");
		doctorTitleDO.setDeleteTime(new Date());
		doctorTitleDO.setDeleteUser(ShiroUtils.getUserId().toString());
		if (doctorTitleService.update(doctorTitleDO) > 0)
//		if(doctorTitleService.remove(id)>0){
		return R.ok();
//		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:doctorTitle:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		for (String id: ids) {
			DoctorTitleDO doctorTitleDO = doctorTitleService.get(id);
			doctorTitleDO.setDelFlag("1");
			doctorTitleDO.setDeleteTime(new Date());
			doctorTitleDO.setDeleteUser(ShiroUtils.getUserId().toString());
			doctorTitleService.update(doctorTitleDO);
		}
		//doctorTitleService.batchRemove(ids);
		return R.ok();
	}
	
}
