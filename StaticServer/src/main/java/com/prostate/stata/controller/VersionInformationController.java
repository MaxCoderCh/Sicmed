package com.prostate.stata.controller;


import com.prostate.stata.entity.VersionInformationDO;
import com.prostate.stata.service.VersionInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * 版本信息
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-08-08 09:29:42
 */
 
@RestController
@RequestMapping(value = "version")
public class VersionInformationController  extends BaseController{


	@Autowired
	private VersionInformationService versionInformationService;


	/**
	 *@Author:      ykbian
	 *@date_time:   2018/8/9 14:55
	 *@Description:  根据版本号查询版本信息
	 *@param:
	*/
	@GetMapping(value = "getByNumber")
	public Map getInformation(String versionNumber){
		VersionInformationDO versionInformationDO = versionInformationService.getByVersionNumber(versionNumber);
		if (versionInformationDO == null){
			return queryEmptyResponse();
		}
		return querySuccessResponse(versionInformationDO);
	}
	
}
