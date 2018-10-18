//package com.sicmed.ehis.registration.feignService;
//
//import com.sicmed.register.feignService.impl.DoctorServerHystrix;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Map;
//
///**
// * 调用 PatientService 中的 API
// */
//@FeignClient(value = "doctor-server",fallback = DoctorServerHystrix.class)
//public interface PatientService {
//
//    @GetMapping(value = "getone")
//    Map<String,Object> getone();
//
//}
