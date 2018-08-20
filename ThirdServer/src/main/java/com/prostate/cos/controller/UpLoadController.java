package com.prostate.cos.controller;

import com.prostate.common.base.BaseController;
import com.prostate.cos.service.CosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "cos")
public class UpLoadController extends BaseController {

    @Autowired
    private CosService cosService;

    private final static Map<String, String> bucketMap = new LinkedHashMap<>();

    UpLoadController() {
        bucketMap.put("checking-records", "checking-records-1256660245");
        bucketMap.put("checkup-records", "checkup-records-1256660245");
        bucketMap.put("diagnosis-records", "diagnosis-records-1256660245");
        bucketMap.put("disease-records", "disease-records-1256660245");
        bucketMap.put("hospital-records", "hospital-records-1256660245");
        bucketMap.put("inspection-records", "inspection-records-1256660245");
        bucketMap.put("doctor-sign", "doctor-sign-1256660245");
        bucketMap.put("user-head", "user-head-1256660245");
        bucketMap.put("person-card", "person-card-1256660245");
        bucketMap.put("patient-record", "patient-record-1256660245");
    }

    @PostMapping(value = "upload")
    public Map upload(@RequestParam("file") MultipartFile file, String recordType) {
        String key = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String bucketName = bucketMap.get(recordType);
        log.info("bucketName=" + bucketName);

        if (!file.isEmpty()) {

            File f = null;
            try {
                f = File.createTempFile("tmp", null);
                file.transferTo(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            f.deleteOnExit();

            cosService.uoload(bucketName, f, key);

            return upLoadSuccessResponse(builderImgPath(bucketName, key));
        }
        return emptyParamResponse();
    }

    @PostMapping(value = "uploads")
    public Map uploads(String recordType, @RequestParam("file") MultipartFile... file) {
        List<String> imgPathList = new ArrayList<>();

        for (MultipartFile multipartFile : file) {
            String key = UUID.randomUUID().toString().replace("-", "") + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String bucketName = bucketMap.get(recordType);
            log.info("bucketName=" + bucketName);

            if (!multipartFile.isEmpty()) {

                File f = null;
                try {
                    f = File.createTempFile("tmp", null);
                    multipartFile.transferTo(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                f.deleteOnExit();

                cosService.uoload(bucketName, f, key);

                imgPathList.add(builderImgPath(bucketName, key));
            }
        }
        return upLoadSuccessResponse(imgPathList);
    }

    private String builderImgPath(String bucketName, String key) {
        StringBuffer imgPath = new StringBuffer();
        imgPath.append("https://");
        imgPath.append(bucketName);
        imgPath.append(".cosbj.myqcloud.com/");
        imgPath.append(key);
        return imgPath.toString();
    }
    /**
     * 删除 上传的图片
     *
     * @param imgPath
     * @return
     */
    @PostMapping(value = "delete")
    public Map delete(String imgPath) {

        String bucketName = imgPath.substring(8, imgPath.indexOf("."));
        String key = imgPath.substring(imgPath.lastIndexOf("/")+1);

        cosService.delete(bucketName, key);

        return deleteSuccseeResponse();
    }
}
