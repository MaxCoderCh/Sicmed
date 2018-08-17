package com.prostate.stata.controller;

import com.prostate.stata.entity.Hospital;
import com.prostate.stata.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "hospital")
public class HospitalController extends BaseController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping(value = "getById")
    public Map<String, Object> getById(String id) {
        Hospital hospital = hospitalService.selectById(id);
        return querySuccessResponse(hospital.getHospitalName());
    }
}
