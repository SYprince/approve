package com.mk.approve.base.utils;


import com.mk.approve.base.constants.ErrorCodeConstants;
import com.mk.approve.base.controller.BaseResponse;

/**
 * @author 张庆贺
 * @since 2020/2/22 20:14
 */
public class ResponseUtils {

    private ResponseUtils() {
    }

    public static BaseResponse setResultSuccess() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCodeConstants.SUCCESS);
        baseResponse.setMsg(ErrorCodeConstants.SUCCESS_MESSAGE);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static BaseResponse setResultSuccess(Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCodeConstants.SUCCESS);
        baseResponse.setMsg(ErrorCodeConstants.SUCCESS_MESSAGE);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse setResultSuccess(String msg, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCodeConstants.SUCCESS);
        baseResponse.setMsg(msg);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse setResultError(String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCodeConstants.SYSTEM_ERROR);
        baseResponse.setMsg(msg);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static BaseResponse setResultError(Integer code, String msg) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        baseResponse.setData(null);
        return baseResponse;
    }

    public static BaseResponse setResult(Integer code, String msg, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        baseResponse.setData(data);
        return baseResponse;
    }
}
