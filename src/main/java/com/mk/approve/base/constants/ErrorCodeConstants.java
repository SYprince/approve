package com.mk.approve.base.constants;

/**
 * 错误码常量类
 * 成功：0
 * 系统异常：-1
 * 用户认证相关： 1000-1010
 * 数据库相关异常：1011-1020
 *
 * @author 张庆贺
 * @since 2020/2/22 20:14
 */
public class ErrorCodeConstants {
    /**
     * 成功
     */
    public static final Integer SUCCESS = 0;

    /**
     * 成功消息描述
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 系统错误-通用异常
     */
    public static final Integer SYSTEM_ERROR = -1;

    /**
     * 登录错误
     */
    public static final Integer AUTH_FAILED = 1000;

}
