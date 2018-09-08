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

import com.prostate.base.domain.EducationDO;
import com.prostate.base.service.EducationService;
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
@RequestMapping("/base/education")
public class EducationController {
	@Autowired
	private EducationService educationService;
	
	@GetMapping()
	@RequiresPermissions("base:education:education")
	String Education(){
	    return "base/education/education";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("base:education:education")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<EducationDO> educationList = educationService.list(query);
		int total = educationService.count(query);
		PageUtils pageUtils = new PageUtils(educationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("base:education:add")
	String add(){
	    return "base/education/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("base:education:edit")
	String edit(@PathVariable("id") String id,Model model){
		EducationDO education = educationService.get(id);
		model.addAttribute("education", education);
	    return "base/education/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("base:education:add")
	public R save(@Validated(GroupWithoutID.class) EducationDO education){
//		if (education.getEducationNumber() == null || education.getEducationNumber().equalsIgnoreCase("")){
//			return R.error(20003,"编号不能为空");
//		}
//		if (education.getEducationName() == null || education.getEducationName().equalsIgnoreCase("")){
//			return R.error(20003,"名称不能为空");
//		}
		//增加数据之前进行判断，如果名称和编号都不重复的话进行下一步
		if(educationService.getByName(education.getEducationName()) == null
				&& educationService.getByNumber(education.getEducationNumber()) == null){
			//获取用户ID作为create_user信息
			education.setCreateTime(new Date());
			education.setCreateUser(ShiroUtils.getUserId().toString());
			education.setDelFlag("0");
			if(educationService.save(education)>0){
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
	@RequiresPermissions("base:education:edit")
	public R update( @Validated({GroupID.class,GroupWithoutID.class})EducationDO education){
//		if (education.getEducationNumber() == null || education.getEducationNumber().equalsIgnoreCase("")){
//			return R.error(20003,"编号不能为空");
//		}
//		if (education.getEducationName() == null || education.getEducationName().equalsIgnoreCase("")){
//			return R.error(20003,"名称不能为空");
//		}
		//根据传入的对象的id获取数据库原来的数据内容
		EducationDO educationDO01 = educationService.get(education.getId());
		//如果新的名称和数据库原来的名称不相同，
		if ( !educationDO01.getEducationName().equalsIgnoreCase(education.getEducationName())){
			//判断新的名字和数据库中其他的数据名称是否冲突
			if(educationService.getByName(education.getEducationName()) != null ){
				return R.error(20001, "名称重复");
			}
		}
		//编号重复上面的检查逻辑
		if( !educationDO01.getEducationNumber().equalsIgnoreCase(education.getEducationNumber())){
			if(educationService.getByNumber(education.getEducationNumber()) != null){
				return R.error(20001, "编号重复");
			}
		}
		education.setUpdateTime(new Date());
		education.setUpdateUser(ShiroUtils.getUserId().toString());
		educationService.update(education);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("base:education:remove")
	public R remove( String id){

		EducationDO educationDO = educationService.get(id);
		if (educationDO == null ){
			return R.error(20003,"这条数据不存在，可能已经删除了");
		}
		educationDO.setDeleteTime(new Date());
		educationDO.setDeleteUser(ShiroUtils.getUserId().toString());
		educationDO.setDelFlag("1");
		if (educationService.update(educationDO)>0){
			return R.ok();
		}
//		if(educationService.remove(id)>0){
//		return R.ok();
//		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("base:education:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		//每一次操作都是和数据库交互两回，拉低了访问速度。
		for (String id: ids) {
			EducationDO educationDO = educationService.get(id);
			if ( educationDO == null){
				return  R.error(20003,"这条数据不存在，可能已经删除了");
			}
			educationDO.setDeleteTime(new Date());
			educationDO.setDeleteUser(ShiroUtils.getUserId().toString());
			educationDO.setDelFlag("1");
			educationService.update(educationDO);
		}
		return R.ok();
	}
	
}
