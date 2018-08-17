package com.prostate.stata.controller;

import com.prostate.stata.entity.LablePatient;
import com.prostate.stata.service.LablePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "lablePatient")
public class LablePatientController extends BaseController {

    @Autowired
    private LablePatientService lablePatientService;

    /**
     * 医生添加 自定义标签
     * @param lableName
     * @return
     */
    @PostMapping(value = "add")
    public Map add(String lableName) {

        LablePatient lablePatient = new LablePatient();
        lablePatient.setCreateUser(getToken());
        lablePatient.setLableName(lableName);
        int result = lablePatientService.insertSelective(lablePatient);

        if (result > 0) {
            return insertSuccseeResponse(lablePatient);
        }
        return insertFailedResponse();

    }


    /**
     * 医生查询 标签
     * @return
     */
    @GetMapping(value = "getAll")
    public Map get() {

        LablePatient lablePatient = new LablePatient();
        lablePatient.setCreateUser(getToken());
        List<LablePatient> lablePatientList = lablePatientService.selectByToken(lablePatient);

        if (lablePatientList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(lablePatientList);

    }
}
