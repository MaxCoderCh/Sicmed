package com.prostate.base.controller;

import com.prostate.base.domain.BloodGroupDO;
import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.base.service.BloodGroupService;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.common.utils.ShiroUtils;
import com.prostate.system.domain.UserOnline;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-05-07 14:02:35
 */

@Controller
@RequestMapping("/base/bloodGroup")
public class BloodGroupController {
    @Autowired
    private BloodGroupService bloodGroupService;

    @GetMapping()
    @RequiresPermissions("base:bloodGroup:bloodGroup")
    String BloodGroup() {
        return "base/bloodGroup/bloodGroup";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("base:bloodGroup:bloodGroup")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BloodGroupDO> bloodGroupList = bloodGroupService.list(query);
        int total = bloodGroupService.count(query);
        PageUtils pageUtils = new PageUtils(bloodGroupList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("base:bloodGroup:add")
    String add() {
        return "base/bloodGroup/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("base:bloodGroup:edit")
    String edit(@PathVariable("id")  String id, Model model) {
        BloodGroupDO bloodGroup = bloodGroupService.get(id);
        model.addAttribute("bloodGroup", bloodGroup);
        return "base/bloodGroup/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("base:bloodGroup:add")
    public R save(@Validated(GroupWithoutID.class) BloodGroupDO bloodGroup) {
        if (bloodGroupService.getByName(bloodGroup.getBloodGroupName()) == null
                && bloodGroupService.getByNumBer(bloodGroup.getBloodGroupNumber()) == null) {
            bloodGroup.setCreateTime(new Date());
            bloodGroup.setCreateUser(ShiroUtils.getUserId().toString());
            bloodGroup.setDelFlag("0");
            if (bloodGroupService.save(bloodGroup) > 0) {
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
    @RequiresPermissions("base:bloodGroup:edit")
    public R update(@Validated({GroupID.class,GroupWithoutID.class}) BloodGroupDO bloodGroup) {
        //根据ID，获取修改前对象的信息
        BloodGroupDO bloodGroup01 = bloodGroupService.get(bloodGroup.getId());
        //如果对象的名称发生变化，
        if (!bloodGroup01.getBloodGroupName().equalsIgnoreCase(bloodGroup.getBloodGroupName())) {
            //根据新的对象名称查询数据库中是否已经存在同名的对象
            BloodGroupDO bloodGroup02 = bloodGroupService.getByName(bloodGroup.getBloodGroupName());
            if (bloodGroup02 != null) {
                return R.error(20001, "名称重复");
            }
        }
        //如果对象的编号发生变化
        if (!bloodGroup01.getBloodGroupNumber().equalsIgnoreCase(bloodGroup.getBloodGroupNumber())) {
            //根据新的对象编号查询数据库中是否已经存在相同编号的对象
            BloodGroupDO bloodGroup02 = bloodGroupService.getByNumBer(bloodGroup.getBloodGroupNumber());
            if (bloodGroup02 != null) {
                return R.error(20001, "编号重复");
            }
        }
        //如果所有的信息都符合条件，获取当前用户信息并修改数据库信息
        bloodGroup.setUpdateTime(new Date());
        bloodGroup.setUpdateUser(ShiroUtils.getUserId().toString());
        bloodGroupService.update(bloodGroup);
        return R.ok();
    }


    /**
     * 删除》》》》》》将del_flag修改为“1”
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("base:bloodGroup:remove")
    public R remove(@Validated(GroupID.class) String id) {
        BloodGroupDO bloodGroup = bloodGroupService.get(id);
        bloodGroup.setDelFlag("1");
        bloodGroup.setDeleteTime(new Date());
        bloodGroup.setDeleteUser(ShiroUtils.getUserId().toString());
        if (bloodGroupService.update(bloodGroup) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 批量删除》》》》》》将del_flag修改为“1”
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("base:bloodGroup:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        for ( String id:ids) {
            BloodGroupDO bloodGroup = bloodGroupService.get(id);
            bloodGroup.setDelFlag("1");
            bloodGroup.setDeleteTime(new Date());
            bloodGroup.setDeleteUser(ShiroUtils.getUserId().toString());
            bloodGroupService.update(bloodGroup);
        }
        return R.ok();
    }

}
