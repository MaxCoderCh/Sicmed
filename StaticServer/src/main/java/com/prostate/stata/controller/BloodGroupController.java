package com.prostate.stata.controller;

import com.prostate.stata.entity.BloodGroup;
import com.prostate.stata.entity.Education;
import com.prostate.stata.entity.Nation;
import com.prostate.stata.service.BloodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "bloodGroup")
public class BloodGroupController extends BaseController{

    @Autowired
    private BloodGroupService bloodGroupService;


    /**
     * 查询血型列表
     * @return
     */
    @PostMapping(value = "getAll")
    public Map getAll(){
        resultMap = new LinkedHashMap<>();
        //查询参数
        BloodGroup bloodGroup = new BloodGroup();
        //查询参数赋值
        
        //调用service 查询数据
        List<BloodGroup> bloodGroupList = bloodGroupService.selectByParams(bloodGroup);
        //查询结果校验 组装 响应
        if(bloodGroupList.size()>0){
            return querySuccessResponse(bloodGroupList);
        }
        return queryEmptyResponse();

    }
    /**
     * 根据Id查询 名称
     *
     * @param id
     * @return
     */
    @GetMapping(value = "getById")
    public Map getById(String id) {
        BloodGroup bloodGroup = bloodGroupService.selectById(id);
        return querySuccessResponse(bloodGroup.getBloodGroupName());
    }
}
