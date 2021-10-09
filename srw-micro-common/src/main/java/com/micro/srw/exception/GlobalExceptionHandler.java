package com.micro.srw.exception;

import com.micro.srw.bean.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description: 全局异常处理
 * @Author: songrenwei
 * @Date: 2020/10/14/15:07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public JsonResult<?> handleException(Exception e) {
        log.error("抛出未捕获异常:", e);
        return JsonResult.error(e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public JsonResult<?> handleBusinessException(BaseException e) {
        log.error("抛出自定义服务异常:", e);
        if (e.getErrorCode() != null) {
            return JsonResult.error(e.getErrorCode(), e.getMessage());
        } else {
            return JsonResult.error(e.getMessage());
        }
    }

}
