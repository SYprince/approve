package com.mk.approve.base.exception;

import com.mk.approve.base.constants.ErrorCodeConstants;
import lombok.Getter;

/**
 * @author 张庆贺
 * @since 2020/2/22 21:23
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 响应码
     */
    private Integer code;

    public BusinessException(String msg) {
        super(msg);
        this.code = ErrorCodeConstants.SYSTEM_ERROR;
    }

    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
