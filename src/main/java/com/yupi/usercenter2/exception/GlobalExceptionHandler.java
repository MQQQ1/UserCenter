package com.yupi.usercenter2.exception;

import com.yupi.usercenter2.common.BaseResponse;
import com.yupi.usercenter2.common.ErrorCode;
import com.yupi.usercenter2.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ClassName：GlobalExceptionHandler
 * Package:com.yupi.usercenter2.exception
 * Description:
 * Author：MQQQ
 *
 * @Create:2023/12/29 - 20:30
 * @Version:v1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获BusinessException异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandler(BusinessException e){
        log.error("businessException:" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(),  e.getMessage(), e.getDescription());
    }
    @ExceptionHandler(BusinessException.class)
    public BaseResponse runtimeExceptionHandler(BusinessException e) {
        log.error("runtimeExceptionHandler:", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
