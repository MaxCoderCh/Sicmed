package com.prostate.stata.controller;

import com.prostate.stata.entity.Nation;
import com.prostate.stata.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "nation")
public class NationController extends BaseController {

    @Autowired
    private NationService nationService;

    /**
     * 查询民族列表
     *
     * @return
     */
    @PostMapping(value = "getAll")
    public Map getAll() {
        resultMap = new LinkedHashMap<>();
        //查询参数
        Nation nation = new Nation();
        //查询参数赋值

        //调用service 查询数据
        List<Nation> nationList = nationService.selectByParams(nation);
        //查询结果校验 组装 响应
        if (nationList.size() > 0) {
            return querySuccessResponse(nationList);
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
        Nation nation = nationService.selectById(id);
        return querySuccessResponse(nation.getNationName());
    }
}
