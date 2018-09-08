package com.prostate.pra.controller;


import com.prostate.common.utils.PageUtils;
import com.prostate.common.utils.Query;
import com.prostate.pra.service.ClickCountDoctorService;
import com.prostate.pra.service.FocusCountDoctorService;
import com.prostate.pra.service.InquriyCountDoctorService;
import com.prostate.pra.vo.CountDoctorVO;
import com.prostate.user.entity.DoctorDetailDO;
import com.prostate.user.service.DoctorDetailsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: ykbian
 * @Date: 2018/8/10 16:23
 * @Todo:   医生数据统计
 */
@Controller
@RequestMapping("/pra/countDoctor")
public class DoctorCountController {

    /**
     * 问诊服务
     */
    @Autowired
    private InquriyCountDoctorService inquriyCountDoctorService;

    /**
     * 关注服务
     */
    @Autowired
    private FocusCountDoctorService focusCountDoctorService;


    /**
     * 点击服务
     */
    @Autowired
    private ClickCountDoctorService clickCountDoctorService;

   /**
    * 医生详情服务
    */
    @Autowired
    private DoctorDetailsService doctorDetailsService;

    @GetMapping()
    @RequiresPermissions("pra:countDoctor:countDoctor")
    String CountDoctor(){
        return "pra/countDoctor/countDoctor";
    }


    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("pra:countDoctor:countDoctor")
    public PageUtils list(@RequestParam Map<String, Object> params){
                Query query = new Query(params);
        List<CountDoctorVO> countDoctorList = new ArrayList<>();
        int total = doctorDetailsService.count(query);
        List<DoctorDetailDO> doctorDetailDOS = doctorDetailsService.list(query);
        for (DoctorDetailDO doctorDetailDO:doctorDetailDOS) {
            String doctorId = doctorDetailDO.getId();
            CountDoctorVO countDoctorVO01 = new CountDoctorVO();
            countDoctorVO01.setClickCount(clickCountDoctorService.countByDoctorId(doctorId));
            countDoctorVO01.setFocusCount(focusCountDoctorService.countByDoctorId(doctorId));
            countDoctorVO01.setInquriyCount(inquriyCountDoctorService.countByDoctorId(doctorId));
            countDoctorVO01.setDoctorName(doctorDetailDO.getDoctorName());
            countDoctorVO01.setDoctorId(doctorId);
            countDoctorList.add(countDoctorVO01);
        }
        PageUtils pageUtils = new PageUtils(countDoctorList, total);
        return pageUtils;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/10 16:50
     *@Description:  跳转医生数据详情页
     *@param:
    */
    @GetMapping("/details/{doctorId}")
    @RequiresPermissions("pra:countDoctor:details")
    String toEdit(@PathVariable("doctorId") String doctorId, Model model){
        model.addAttribute("doctorId", doctorId);
        return "pra/countDoctor/details";
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/13 9:43
     *@Description: 默认查询近一周的数据
     *@param:   ==============================返回值json和map均可，主要是看前端需求
    */
    @ResponseBody
    @GetMapping("/weekDetails/{doctorId}")
    public Map viewOfWeek(@PathVariable("doctorId") String doctorId){
        Map map = new HashMap();
        //日期数组
        String[] dates = new String[7];
        for (int i = 6;i>=0;i--){
            //获取今天的日期
            Date today = new Date();
            //86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
            Date date = new Date(today.getTime() - 86400000L*i);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            dates[6-i] = dateStr;
        }
        //问诊人数数组
        int[] inquriy = inquriyCountDoctorService.countThisWeek(doctorId);
        //点击人数数组
        int[] click = clickCountDoctorService.countThisWeek(doctorId);
        //关注人数数组
        int[] focus = focusCountDoctorService.countThisWeek(doctorId);
        map.put("name",doctorDetailsService.get(doctorId).getDoctorName());
        map.put("dates",dates);
        map.put("inquriy",inquriy);
        map.put("click",click);
        map.put("focus",focus);
        return map;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/13 15:04
     *@Description: 查询最近三十天的数据
     *@param:
    */
    @ResponseBody
    @GetMapping("/moothDetails/{doctorId}")
    public Map viewOfMooth(@PathVariable("doctorId") String doctorId){
        Map map = new HashMap();
        //日期数组
        String[] dates = new String[31];
        for (int i = 30;i>=0;i--){
            //获取今天的日期
            Date today = new Date();
            //86400000L，它的意思是说1天的时间=24小时 x 60分钟 x 60秒 x 1000毫秒 单位是L。
            Date date = new Date(today.getTime() - 86400000L*i);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            dates[30-i] = dateStr;
        }
       //问诊人数数组
        int[] inquriy = inquriyCountDoctorService.countThisMooth(doctorId);
        //点击人数数组
        int[] click = clickCountDoctorService.countThisMooth(doctorId);
        //关注人数数组
        int[] focus = focusCountDoctorService.countThisMooth(doctorId);
        map.put("name",doctorDetailsService.get(doctorId).getDoctorName());
        map.put("dates",dates);
        map.put("inquriy",inquriy);
        map.put("click",click);
        map.put("focus",focus);
        return map;
    }


    /**
     *@Author:      ykbian
     *@date_time:   2018/8/13 15:36
     *@Description: 查询最近的十二个月的数据
     *@param:
    */
    @ResponseBody
    @GetMapping("/yearDetails/{doctorId}")
    public Map viewOfYear(@PathVariable("doctorId") String doctorId){
        Map map = new HashMap();
        //日期数组
        String[] dates = new String[12];
        for (int i = 11;i>=0;i--){
           // 固定的获取月份数字
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -i);
            Date m = c.getTime();
            String mon = format.format(m);
            dates[11-i] = mon;
        }
      //问诊人数数组
        int[] inquriy = inquriyCountDoctorService.countThisYear(doctorId);
        //点击人数数组
        int[] click = clickCountDoctorService.countThisYear(doctorId);
        //关注人数数组
        int[] focus = focusCountDoctorService.countThisYear(doctorId);
        map.put("name",doctorDetailsService.get(doctorId).getDoctorName());
        map.put("dates",dates);
        map.put("inquriy",inquriy);
        map.put("click",click);
        map.put("focus",focus);
        return map;
    }
}
