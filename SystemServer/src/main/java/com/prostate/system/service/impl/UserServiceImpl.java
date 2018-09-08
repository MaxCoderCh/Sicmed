package com.prostate.system.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

import com.prostate.common.config.ProstateConfig;
import com.prostate.common.domain.FileDO;
import com.prostate.common.service.FileService;
import com.prostate.common.utils.*;
import com.prostate.system.mapper.slaver.DeptReadMapper;
import com.prostate.system.mapper.slaver.UserReadMapper;
import com.prostate.system.mapper.slaver.UserRoleReadMapper;
import com.prostate.system.mapper.master.DeptWriteMapper;
import com.prostate.system.mapper.master.UserRoleWriteMapper;
import com.prostate.system.mapper.master.UserWriteMapper;
import com.prostate.system.vo.UserVO;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prostate.common.domain.Tree;
import com.prostate.system.domain.DeptDO;
import com.prostate.system.domain.UserDO;
import com.prostate.system.domain.UserRoleDO;
import com.prostate.system.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserWriteMapper userWriteMapper;
    @Autowired
    private UserReadMapper userReadMapper;
    @Autowired
    private UserRoleWriteMapper userRoleWriteMapper;
    @Autowired
    private UserRoleReadMapper userRoleReadMapper;
    @Autowired
    private DeptWriteMapper deptWriteMapper;
    @Autowired
    private DeptReadMapper deptReadMapper;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private ProstateConfig prostateConfig;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDO get(Long id) {
        List<Long> roleIds = userRoleReadMapper.listRoleId(id);
        UserDO user = userReadMapper.get(id);
        user.setDeptName(deptReadMapper.get(user.getDeptId()).getName());
        user.setRoleIds(roleIds);
        return user;
    }

    @Override
    public List<UserDO> list(Map<String, Object> map) {
        return userReadMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return userReadMapper.count(map);
    }

    @Transactional
    @Override
    public int save(UserDO user) {
        int count = userWriteMapper.save(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleWriteMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleWriteMapper.batchSave(list);
        }
        return count;
    }

    @Override
    public int update(UserDO user) {
        int r = userWriteMapper.update(user);
        Long userId = user.getUserId();
        List<Long> roles = user.getRoleIds();
        userRoleWriteMapper.removeByUserId(userId);
        List<UserRoleDO> list = new ArrayList<>();
        for (Long roleId : roles) {
            UserRoleDO ur = new UserRoleDO();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleWriteMapper.batchSave(list);
        }
        return r;
    }

    @Override
    public int remove(Long userId) {
        userRoleWriteMapper.removeByUserId(userId);
        return userWriteMapper.remove(userId);
    }

    @Override
    public boolean exit(Map<String, Object> params) {
        boolean exit;
        exit = userReadMapper.list(params).size() > 0;
        return exit;
    }

    @Override
    public Set<String> listRoles(Long userId) {
        return null;
    }

    @Override
    public int resetPwd(UserVO userVO, UserDO userDO) throws Exception {
        if (Objects.equals(userVO.getUserDO().getUserId(), userDO.getUserId())) {
            if (Objects.equals(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdOld()), userDO.getPassword())) {
                userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
                return userWriteMapper.update(userDO);
            } else {
                throw new Exception("输入的旧密码有误！");
            }
        } else {
            throw new Exception("你修改的不是你登录的账号！");
        }
    }

    @Override
    public int adminResetPwd(UserVO userVO) throws Exception {
        UserDO userDO = get(userVO.getUserDO().getUserId());
        if ("admin".equals(userDO.getUsername())) {
            throw new Exception("超级管理员的账号不允许直接重置！");
        }
        userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
        return userWriteMapper.update(userDO);


    }

    @Transactional
    @Override
    public int batchremove(Long[] userIds) {
        int count = userWriteMapper.batchRemove(userIds);
        userRoleWriteMapper.batchRemoveByUserId(userIds);
        return count;
    }

    @Override
    public Tree<DeptDO> getTree() {
        // 创建一个树形结构的集合
        List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
        //读取所有的部门信息
        List<DeptDO> depts = deptReadMapper.list(new HashMap<String, Object>(16));
        //读取所有的上级部门id存入一个数组
        Long[] pDepts = deptReadMapper.listParentDept();
        //读取所有用户的部门id存入数组
        Long[] uDepts = userReadMapper.listAllDept();
        //所有的id都存入集合并转为Long数组
        Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
        for (DeptDO dept : depts) {
            //如果Long数组包含dept的id信息，则跳过这次循序
            if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
                continue;
            }
            //创建一个树形结构
            Tree<DeptDO> tree = new Tree<DeptDO>();
            //依次设置节点的id，父id，显示文本和一些状态信息
            tree.setId(dept.getDeptId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getName());
            //创建一个map集合，
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "dept");
            tree.setState(state);
            //这个节点存入集合
            trees.add(tree);
        }
        List<UserDO> users = userReadMapper.list(new HashMap<String, Object>(16));
        for (UserDO user : users) {
            Tree<DeptDO> tree = new Tree<DeptDO>();
            tree.setId(user.getUserId().toString());
            tree.setParentId(user.getDeptId().toString());
            tree.setText(user.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            state.put("mType", "user");
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<DeptDO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public int updatePersonal(UserDO userDO) {
        return userWriteMapper.update(userDO);
    }

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        //获取图片后缀
        String prefix = fileName.substring((fileName.lastIndexOf(".") + 1));
        String[] str = avatar_data.split(",");
        //获取截取的x坐标
        int x = (int) Math.floor(Double.parseDouble(str[0].split(":")[1]));
        //获取截取的y坐标
        int y = (int) Math.floor(Double.parseDouble(str[1].split(":")[1]));
        //获取截取的高度
        int h = (int) Math.floor(Double.parseDouble(str[2].split(":")[1]));
        //获取截取的宽度
        int w = (int) Math.floor(Double.parseDouble(str[3].split(":")[1]));
        //获取旋转的角度
        int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
        try {
            BufferedImage cutImage = ImageUtils.cutImage(file, x, y, w, h, prefix);
            BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(rotateImage, prefix, out);
            //转换后存入数据库
            byte[] b = out.toByteArray();
            FileUtil.uploadFile(b, prostateConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            throw new Exception("图片裁剪错误！！");
        }
        Map<String, Object> result = new HashMap<>();
        if (sysFileService.save(sysFile) > 0) {
            UserDO userDO = new UserDO();
            userDO.setUserId(userId);
            userDO.setPicId(sysFile.getId());
            if (userWriteMapper.update(userDO) > 0) {
                result.put("url", sysFile.getUrl());
            }
        }
        return result;
    }

}
