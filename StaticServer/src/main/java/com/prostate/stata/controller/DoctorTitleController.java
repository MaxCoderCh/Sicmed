package com.prostate.stata.controller;

import com.prostate.stata.entity.DoctorTitle;
import com.prostate.stata.service.DoctorTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "title")
public class DoctorTitleController extends BaseController {

    @Autowired
    private DoctorTitleService doctorTitleService;

    @GetMapping(value = "getById")
    public Map<String, Object> getById(String id) {
        DoctorTitle doctorTitle = doctorTitleService.selectById(id);
        return querySuccessResponse(doctorTitle.getDoctorTitleName());
    }
}