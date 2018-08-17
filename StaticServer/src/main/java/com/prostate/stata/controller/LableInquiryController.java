package com.prostate.stata.controller;

import com.prostate.stata.entity.LableInquiry;
import com.prostate.stata.service.LableInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "lableInquiry")
public class LableInquiryController extends BaseController {

    @Autowired
    private LableInquiryService lableInquiryService;

    /**
     * 查询 所有的会诊标签
     * @return
     */
    @GetMapping(value = "getAll")
    public Map<String, Object> get() {

        List<LableInquiry> lableInquiryList = lableInquiryService.selectByParams(null);

        if (lableInquiryList.isEmpty()) {
            return queryEmptyResponse();
        }
        return querySuccessResponse(lableInquiryList);

    }


}
