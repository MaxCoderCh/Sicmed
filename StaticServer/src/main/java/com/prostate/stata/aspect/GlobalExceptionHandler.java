/**
 * 全局异常捕获
 */
package com.prostate.stata.aspect;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Map<String, Object> resultMap;

    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        resultMap = new LinkedHashMap<>();
//        log.error("我报错了：{}", exception.getLocalizedMessage());
//        log.error("我报错了：{}", exception.getCause());
//        log.error("我报错了：{}",exception.getSuppressed());
//        log.error("我报错了：{}", exception.getMessage());
        log.error("我报错了：{}",exception.getStackTrace());
        resultMap.put("code", "50000");
        resultMap.put("msg", "SYSTEM_ERROR");
        resultMap.put("result", "服务器异常!");

        return resultMap;
    }

}
