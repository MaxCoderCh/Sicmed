package com.prostate.user.controller;

import com.prostate.base.domain.BranchDO;
import com.prostate.base.domain.DoctorTitleDO;
import com.prostate.base.domain.HospitalDO;
import com.prostate.user.config.BaseConstant;
import com.prostate.user.entity.DoctorSignDO;
import com.prostate.base.service.BranchService;
import com.prostate.user.service.DoctorSignService;
import com.prostate.base.service.DoctorTitleService;
import com.prostate.base.service.HospitalService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.common.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生认证信息表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-07 10:26:55
 */

@Controller
@RequestMapping("/user/doctorSign")
public class DoctorSignController {
	@Autowired
	private DoctorSignService signService;

	/**
	 *  医生职称
	 */
	@Autowired
	private DoctorTitleService doctorTitleService;

	/**
	 * 医院信息
	 */
	@Autowired
	private HospitalService hospitalService;


	/**
	 * 科室信息
	 */
	@Autowired
	private BranchService branchService;



	@GetMapping()
	@RequiresPermissions("user:doctorSign:doctorSign")
	String Sign(){
	    return "user/doctorSign/doctorSign";
	}



	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/8 9:10
	 *@Description:  查询待认证医生列表
	 *@param:
	*/
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("user:doctorSign:doctorSign")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//此处查询的都是未处理的订单信息
		//查询列表数据
		System.out.println("=================");

		Query query = new Query(params);
		List<DoctorSignDO> signList = signService.list(query);
		for (DoctorSignDO doctorSignDO: signList) {   //=================================以下可能会报空指针
		/**
		 * 这里需要获取医生信息
		 * */
			//doctorSignDO.setDoctorId("医生姓名+"+doctorSignDO.getDoctorId());
			//将所有的id都变为文字信息
			DoctorTitleDO title = doctorTitleService.get(doctorSignDO.getTitleId());
			HospitalDO hospital = hospitalService.get(doctorSignDO.getHospitalId());
			BranchDO branchDO = branchService.get(doctorSignDO.getBranchId());
			if(title != null){
				doctorSignDO.setTitleId(title.getDoctorTitleName());
			}
			if(hospital != null){
				doctorSignDO.setHospitalId(hospital.getHospitalName());
			}
			if(branchDO != null){
				doctorSignDO.setBranchId(branchDO.getBranchName());
			}
		}
		int total = signService.count(query);
		PageUtils pageUtils = new PageUtils(signList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("user:doctorSign:add")
	String add(){
	    return "user/doctorSign/add";
	}



//	@GetMapping("/edit/{id}")
//	@RequiresPermissions("base:doctorSign:edit")
//	String edit(@PathVariable("id") String id,Model model){
//		DoctorSignDO sign = signService.get(id);
//		model.addAttribute("doctorSign", sign);
//	    return "base/doctorSign/edit";
//	}


	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/8 9:09
	 *@Description:   查询医生的详细信息
	 *@param:
	*/
	@GetMapping("/details/{id}")
	@RequiresPermissions("user:doctorSign:details")
	String details(@PathVariable("id") String id,Model model){

		DoctorSignDO doctorSignDO = signService.get(id);
		//把认证信息为“2”的医生状态都改写为“认证中”，便于前端展示
		if (doctorSignDO.getApproveStatus().equalsIgnoreCase(BaseConstant.AUTHENTICATION_PROGRESS)){
			doctorSignDO.setApproveStatus("认证中");
		}
		model.addAttribute("doctorSign", doctorSignDO);
		/**
		 * 这里需要获取医生信息
		 * */

		model.addAttribute("doctorId","此处显示医生姓名");
		DoctorTitleDO title = doctorTitleService.get(doctorSignDO.getTitleId());
		HospitalDO hospital = hospitalService.get(doctorSignDO.getHospitalId());
		BranchDO branchDO = branchService.get(doctorSignDO.getBranchId());
		if(title != null){
			model.addAttribute("titleId",title.getDoctorTitleName());
		}
		if(hospital != null){
			model.addAttribute("hospitalId",hospital.getHospitalName());
		}
		if(branchDO != null){
			model.addAttribute("branchId",branchDO.getBranchName());
		}
		return "user/doctorSign/details";
	}



	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("user:doctorSign:add")
	public R save( DoctorSignDO sign){
		if(signService.save(sign)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/7 13:24
	 *@Description:  认证成功，改状态码，发送短信
	 *@param:
	*/
	@ResponseBody
	@RequestMapping("/pass")
	@RequiresPermissions("user:doctorSign:edit")
	public R pass( DoctorSignDO sign){

		//获取当前用户的token信息
		String userToken = ShiroUtils.getUserId().toString();
		sign.setApproveStatus(BaseConstant.AUTHENTICATION_SUCCESS);
		sign.setUpdateUser(userToken);
		if (signService.update(sign) > 0){
			/**
			 * 此处发短信
			 *
			 * */
			return R.ok();
		}else {
			return R.error(20001,"操作失败");
		}


	}



	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/7 13:51
	 *@Description:   拒绝医生认证信息
	 *@param:
	*/
	@ResponseBody
	@RequestMapping("/refuse")
	@RequiresPermissions("user:doctorSign:edit")
	public R refuse( DoctorSignDO sign){
		//获取当前用户的token信息
		String userToken = ShiroUtils.getUserId().toString();
		sign.setUpdateUser(userToken);
		sign.setApproveStatus(BaseConstant.AUTHENTICATION_FAILED);
		if (signService.update(sign) > 0){
			/**
			 * 此处发短信
			 *
			 * */
			return R.ok();
		}else {
			return R.error(20001,"操作失败");
		}

	}


	/**
	 * 
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("user:doctorSign:remove")
	public R remove( String id){
		if(signService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("user:doctorSign:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		signService.batchRemove(ids);
		return R.ok();
	}

}
