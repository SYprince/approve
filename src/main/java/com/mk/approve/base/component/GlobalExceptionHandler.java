package com.mk.approve.base.component;

import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.exception.BusinessException;
import com.mk.approve.base.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用异常处理
 *
 * @author 张庆贺
 * @since 2020/2/22 21:21
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception e) {
        //全局异常处理逻辑
        if (e instanceof BusinessException) {
            log.error("业务异常，错误信息{}", e.getMessage());
            return ResponseUtils.setResultError(e.getMessage());
        } else {
            log.error("系统异常，错误信息{}", e.getMessage(), e);
            return ResponseUtils.setResultError("系统异常");
        }
    }

}
