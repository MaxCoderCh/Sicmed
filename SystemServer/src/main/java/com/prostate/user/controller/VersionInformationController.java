package com.prostate.user.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prostate.user.entity.VersionInformationDO;
import com.prostate.user.service.VersionInformationService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;

/**
 * 版本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
 
@Controller
@RequestMapping("/user/information")
public class VersionInformationController {
	@Autowired
	private VersionInformationService versionInformationService;
	
	@GetMapping()
	@RequiresPermissions("user:information:information")
	String Information(){
	    return "user/information/information";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("user:information:information")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<VersionInformationDO> informationList = versionInformationService.list(query);
		//只是简单的页面列表展示，因此先展示25个字。
		for (VersionInformationDO versionInformationDO:informationList) {
			String str = versionInformationDO.getVersionDetails();
			if (str.length()>50){
				str = str.substring(0,50)+"……";
			}
			versionInformationDO.setVersionDetails(str);
		}
		int total = versionInformationService.count(query);
		PageUtils pageUtils = new PageUtils(informationList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("user:information:add")
	String add(){
	    return "user/information/add";
	}



	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/8 17:03
	 *@Description:   这个是跳转编辑信息的界面，也可以看做是跳转查看详情的页面
	 *@param:
	*/
	@GetMapping("/edit/{id}")
	@RequiresPermissions("user:information:edit")
	String edit(@PathVariable("id") String id,Model model){
		VersionInformationDO information = versionInformationService.get(id);
		model.addAttribute("information", information);
	    return "user/information/edit";
	}




	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("user:information:add")
	public R save( VersionInformationDO information){
		if (information.getVersionDetails().length()>1000){
			return R.error();
		}
		information.setCreateUser(ShiroUtils.getUserId().toString());
		information.setCreateTime(new Date());
		if(versionInformationService.save(information)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("user:information:edit")
	public R update( VersionInformationDO information){
		versionInformationService.update(information);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("user:information:remove")
	public R remove( String id){
		if(versionInformationService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("user:information:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		versionInformationService.batchRemove(ids);
		return R.ok();
	}
	
}
