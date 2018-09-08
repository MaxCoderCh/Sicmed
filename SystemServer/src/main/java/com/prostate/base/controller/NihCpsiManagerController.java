package com.prostate.base.controller;

import com.prostate.base.domain.GroupID;
import com.prostate.base.domain.GroupWithoutID;
import com.prostate.base.domain.NihCpsiDO;
import com.prostate.base.service.NihCpsiManagerService;
import com.prostate.common.annotation.Log;
import com.prostate.common.domain.Tree;
import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.common.utils.R;
import com.prostate.common.utils.ShiroUtils;
import com.prostate.system.domain.RoleDO;
import com.prostate.system.domain.UserDO;
import com.prostate.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: developerfengrui
 * @Description:
 * @Date: Created in 11:58 2018/5/21
 */
@Controller
@RequestMapping("/base/nihCpsiManager")
public class NihCpsiManagerController {

    private String prefix="base/nihCpsiManager";
    @Autowired
    private NihCpsiManagerService nihCpsiManagerService;

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("base:nihCpsiManager:nihCpsiManager")
    @GetMapping("")
    String user(Model model) {
        System.out.println();
        return prefix + "/nihCpsiManager";
    }


    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        if(params.get("parentId")==null||params.get("parentId").equals("")){
            query.put("nihCpsiType","1");
        }
        List<NihCpsiDO> nihCpsiDOList = nihCpsiManagerService.list(query);

        int total = nihCpsiManagerService.count(query);
        PageUtils pageUtil = new PageUtils(nihCpsiDOList, total);
        return pageUtil;
    }

    @RequiresPermissions("base:nihCpsiManager:add")
    @Log("添加题目或选项")
    @GetMapping("/add")
    String add(Model model) {
        List<RoleDO> roles = roleService.list();
        model.addAttribute("roles", roles);
        return prefix + "/add";
    }

    @RequiresPermissions("base:nihCpsiManager:edit")
    @Log("编辑题目或选项")
    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable("id") String id) {
        NihCpsiDO nihCpsiDO = nihCpsiManagerService.get(id);
        model.addAttribute("nihCpsiDO", nihCpsiDO);
        NihCpsiDO nihCpsi= nihCpsiManagerService.get(nihCpsiDO.getParentId());
        model.addAttribute("nihCpsi",nihCpsi);
        return prefix+"/edit";
    }

    @RequiresPermissions("base:nihCpsiManager:add")
    @Log("保存题目或选项")
    @PostMapping("/save")
    @ResponseBody
    R save(@Validated(GroupWithoutID.class)NihCpsiDO nihCpsiDO) {

        NihCpsiDO nihCpsi=nihCpsiManagerService.get(nihCpsiDO.getParentId());
        if (nihCpsi!=null||nihCpsi.equals("")){
            if(nihCpsi.getNihCpsiType().equalsIgnoreCase("a")||
                    nihCpsi.getNihCpsiType().equalsIgnoreCase("b")||
                    nihCpsi.getNihCpsiType().equalsIgnoreCase("c")){
                nihCpsiDO.setNihCpsiType("1");
            }else if (nihCpsi.getNihCpsiType().equalsIgnoreCase("1")){
                nihCpsiDO.setNihCpsiType("0");
            }
        }

        // if (scaleManagerService.listByName(scaleDO.getCityName()).size()==0){
        nihCpsiDO.setCreateUser(ShiroUtils.getUserId().toString());
        // scaleDO.setUpdateUser(ShiroUtils.getUserId().toString());
        if(nihCpsiManagerService.save(nihCpsiDO)>0){
            return R.ok();
        }

        //  }
        return R.error(20001,"该题目或选项已经存在");


    }

    @RequiresPermissions("base:nihCpsiManager:edit")
    @Log("更新题目或选项")
    @PostMapping("/update")
    @ResponseBody
    R update(@Validated({GroupID.class,GroupWithoutID.class})NihCpsiDO nihCpsiDO) {


        NihCpsiDO nihCpsi=nihCpsiManagerService.get(nihCpsiDO.getParentId());
        if (nihCpsi!=null||nihCpsi.equals("")){
            if(nihCpsi.getNihCpsiType().equalsIgnoreCase("a")||
                    nihCpsi.getNihCpsiType().equalsIgnoreCase("b")||
                    nihCpsi.getNihCpsiType().equalsIgnoreCase("c")){
                nihCpsiDO.setNihCpsiType("1");
            }else if (nihCpsi.getNihCpsiType().equalsIgnoreCase("1")){
                nihCpsiDO.setNihCpsiType("0");
            }
        }

        if (nihCpsiManagerService.update(nihCpsiDO)>0){
            return R.ok();
        }else {
            return R.error();
        }

    }


    @RequiresPermissions("base:nihCpsiManager:edit")
    @Log("更新题目或选项")
    @PostMapping("/updatePeronal")
    @ResponseBody
    R updatePeronal(UserDO user) {
        return R.error();
    }


    @RequiresPermissions("base:nihCpsiManager:remove")
    @Log("删除题目或选项")
    @PostMapping("/remove")
    @ResponseBody
    R remove(String id) {

        NihCpsiDO nihCpsiDO =nihCpsiManagerService.get(id);
        nihCpsiDO.setDeleteTime(new Date());
        nihCpsiDO.setDeleteUser(ShiroUtils.getUserId().toString());
        nihCpsiDO.setDelFlag("1");
        if (nihCpsiManagerService.update(nihCpsiDO) > 0) {
            return R.ok();
        }
        return R.error();
    }

    @RequiresPermissions("base:nihCpsiManager:batchRemove")
    @Log("批量删除题目或选项")
    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") String[] ids) {
        for (String id:ids){
            NihCpsiDO nihCpsiDO=nihCpsiManagerService.get(id);
            nihCpsiDO.setId(id);
            nihCpsiDO.setDeleteUser(ShiroUtils.getUserId().toString());
            nihCpsiDO.setDeleteTime(new Date());
            nihCpsiDO.setDelFlag("1");
            nihCpsiManagerService.update(nihCpsiDO);
        }

        return R.ok();
    }





    @GetMapping("/tree")
    @ResponseBody
    public Tree<NihCpsiDO> tree() {
        Tree<NihCpsiDO> tree = new Tree<NihCpsiDO>();
        tree = nihCpsiManagerService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    String treeView() {
        return  prefix + "/nihCpsiManagerTree";
    }




}
