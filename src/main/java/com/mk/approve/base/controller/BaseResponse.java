package com.mk.approve.base.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用响应结构体，不能有构造函数，否者不能反序列化
 *
 * @author 张庆贺
 * @since 2020/2/22 20:08
 */
@Getter
@Setter
public class BaseResponse {
    private Integer code;
    private String msg;
    private Object data;
}
