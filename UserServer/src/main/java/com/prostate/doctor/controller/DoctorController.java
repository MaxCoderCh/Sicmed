package com.prostate.doctor.controller;

import com.prostate.doctor.entity.Doctor;
import com.prostate.doctor.param.DoctorRegisteParams;
import com.prostate.doctor.service.DoctorService;
import com.prostate.doctor.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("doctor")
public class DoctorController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private JsonUtils<Doctor> jsonUtil;


    /**
     * 手机号 短信验证码 注册 接口
     * @param doctorRegisteParams
     * @return
     */
    @PostMapping(value = "register")
    public Map registerDoctor(@Valid DoctorRegisteParams doctorRegisteParams) {

        String doctorPhone = doctorRegisteParams.getDoctorPhone();
        String smsCode = doctorRegisteParams.getSmsCode();
        String doctorPassword = doctorRegisteParams.getDoctorPassword();


        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor != null) {
            return registerFiledResponse("手机号码已注册过");
        }
        //短信验证码校验
        String ck = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(ck)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(ck)) {
            return failedRequest("手机号码不一致");
        }
        //手机号重复注册数据校验
        doctor = new Doctor();
        doctor.setDoctorPhone(doctorPhone);
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()));

        //这里做一次数据检查

        int result = doctorService.insertSelective(doctor);
        if (result > 0) {
            return registerSuccseeResponse("注册成功");
        }
        return registerFiledResponse("注册失败,该手机号已被注册");
    }


    /**
     * 手机号 密码 登陆 接口
     * @param doctorPhone
     * @param doctorPassword
     * @return
     */
    @PostMapping(value = "login")
    public Map loginDoctor(String doctorPhone, String doctorPassword) {
        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("用户名或密码不正确");
        }
        String salt = doctor.getSalt();
        if (doctor.getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()))) {
            String token = doctor.getId();
            redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));
            log.info("======登陆成功====");
            return loginSuccessResponse(token);
        } else {
            String token = doctor.getId();
            redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));
            log.info("======登陆成功====");
            return loginSuccessResponse(token);
//            log.info("======登陆失败====");
//            return loginFailedResponse("用户名或密码不正确");
        }
    }

    /**
     * HIS 系统 密码 登陆 接口
     * @param doctorPhone
     * @param doctorPassword
     * @return
     */
    @PostMapping(value = "his/login")
    public Map hisLoginDoctor(String doctorPhone, String doctorPassword) {
        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("用户名或密码不正确");
        }
        String salt = doctor.getSalt();
        if (doctor.getDoctorPassword().equals(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()))) {
            String token = doctor.getId();
            redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));
            log.info("======EHIS系统登陆成功====");
            String role = doctor.getUserRole();
            return hisLoginSuccessResponse(token,role);
        } else {
            log.info("======EHIS系统登陆成功====");
            return loginFailedResponse("用户名或密码不正确");
        }
    }
    /**
     * 手机号 短信验证码 登陆 接口
     *
     * @param doctorPhone
     * @param smsCode
     */
    @PostMapping(value = "smsLogin")
    public Map smsLogin(String doctorPhone, String smsCode) {

        //短信验证码校验
        String cachePhone = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(cachePhone)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(cachePhone)) {
            return failedRequest("手机号码错误");
        }
        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("手机号或验证码错误");
        }
        String token = doctor.getId();
        redisSerive.insert(token, jsonUtil.objectToJsonStr(doctor));
        return loginSuccessResponse(token);
    }

    /**
     * 重设 登陆密码
     * @param doctorPhone
     * @param smsCode
     * @param doctorPassword
     * @return
     */
    @PostMapping(value = "passwordReset")
    public Map passwordReset(String doctorPhone, String smsCode, String doctorPassword) {
        //短信验证码校验
        String cachePhone = redisSerive.getSmsCode(smsCode);
        if (StringUtils.isEmpty(cachePhone)) {
            return failedRequest("验证码已过期!");
        } else if (!doctorPhone.equals(cachePhone)) {
            return failedRequest("手机号码错误");
        }

        Doctor doctor = doctorService.selectByPhone(doctorPhone);
        if (doctor == null) {
            return loginFailedResponse("手机号或验证码错误");
        }
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((doctorPassword + salt).getBytes()));

        int i = doctorService.updateSelective(doctor);
        if (i > 0) {
            redisSerive.remove(doctor.getId());
            return updateSuccseeResponse("密码重置成功");
        }
        return updateFailedResponse("密码重置失败");
    }

    /**
     * 根据旧密码修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping(value = "updatePassword")
    public Map updatePassword(String oldPassword, String newPassword) {

        if(StringUtils.isBlank(newPassword)||newPassword.length()<6){
            return newPasswordFailedResponse("新密码格式不正确");
        }
        if (!this.equalsPassword(oldPassword)) {
            return oldPasswordFailedResponse("旧密码不正确");
        }
        if (oldPassword.equals(newPassword)) {
            return oldPasswordEqualsNewPasswordResponse("新密码与旧密码一样");
        }

        Doctor doctor = redisSerive.getDoctor();
        //生成盐
        String salt = RandomStringUtils.randomAlphanumeric(32).toLowerCase();
        //设置盐
        doctor.setSalt(salt);
        //md5密码加密
        doctor.setDoctorPassword(DigestUtils.md5DigestAsHex((newPassword + salt).getBytes()));

        int i = doctorService.updateSelective(doctor);
        if (i > 0) {
//            redisSerive.insert(doctor.getId(), jsonUtil.objectToJsonStr(doctor));
            redisSerive.remove(doctor.getId());
            return updatePasswordSuccessResponse("密码重置成功");
        }
        return updatePasswordFailedResponse("密码重置失败");
    }


    /**
     * 获取用户登陆信息
     * @return
     */
    @GetMapping(value = "getUsername")
    public Map<String, Object> getUsername(){

        Doctor doctor = redisSerive.getDoctor();

        return querySuccessResponse(doctor.getDoctorPhone());
    }


    /**
     * 获取注册 短信验证码
     *
     * @param registerPhone
     * @return
     */
    @GetMapping(value = "registerSms")
    public Map registerSms(String registerPhone) {

        Doctor doctor = doctorService.selectByPhone(registerPhone);

        if (doctor != null) {
            return registerFiledResponse("手机号码已注册过");
        }
        try {
            feignService.ThirdServerSendRegisterCode(registerPhone);
        } catch (Exception e) {
            log.error("发送注册验证码到 phoneNumber:" + registerPhone + "失败");
            return insertFailedResponse(null);
        }
        return insertSuccseeResponse(null);

    }


    /**
     * 获取 登陆 短信验证码
     *
     * @param loginPhone
     * @return
     */
    @GetMapping(value = "loginSms")
    public Map loginSms(String loginPhone) {

        Doctor doctor = doctorService.selectByPhone(loginPhone);

        if (doctor == null) {
            return loginFailedResponse("手机号码未注册!");
        }
        try {
            feignService.ThirdServerSendLoginCode(loginPhone);
        } catch (Exception e) {
            log.info("发送登陆验证码到 phoneNumber:" + loginPhone + "失败");
            return insertFailedResponse(null);
        }
        return insertSuccseeResponse(null);
    }

    /**
     * 获取 修改密码 短信验证码
     *
     * @param passwordPhone
     * @return
     */
    @GetMapping(value = "passwordSms")
    public Map passwordSms(String passwordPhone) {
        Doctor doctor = doctorService.selectByPhone(passwordPhone);

        if (doctor == null) {
            return loginFailedResponse("手机号码未注册!");
        }
        try {
            feignService.ThirdServerSendPasswordReplaceCode(passwordPhone);
        } catch (Exception e) {
            log.info("发送密码重置验证码到 phoneNumber:" + passwordPhone + "失败");
            return insertFailedResponse(null);
        }
        return insertSuccseeResponse(null);
    }

    @PostMapping(value = "logOut")
    public Map logOut() {

        redisSerive.remove(getToken());

        return logOutSuccessResponse("退出成功");
    }

    /**
     * 校验密码是否是旧密码
     *
     * @param password
     * @return
     */
    private boolean equalsPassword(String password) {

        Doctor doctor = redisSerive.getDoctor();

        if (doctor == null) {
            return false;
        }
        String salt = doctor.getSalt();
        if (doctor.getDoctorPassword().equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Provider
     */
    @GetMapping(value = "getPhoneNumber")
    public String getPhoneNumber(String userId) {

        String phoneNumber = doctorService.getPhoneNumberById(userId);

        if (StringUtils.isBlank(phoneNumber)) {
            return "ERROR";
        }
        return phoneNumber;
    }

}
