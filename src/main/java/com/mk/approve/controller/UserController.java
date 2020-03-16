package com.mk.approve.controller;

import com.mk.approve.base.controller.BaseController;
import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.utils.ResponseUtils;
import com.mk.approve.entity.UserInfo;
import com.mk.approve.service.IUserInfoBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 16:08 2020/3/12
 */
@RestController
@RequestMapping("/userController")
@Slf4j
@Api(value = "用户相关接口")
public class UserController extends BaseController<IUserInfoBaseService, UserInfo> {

    @ApiOperation(value = "查询全部某一级审批人", notes = "一级审批人传2，二级审批人传3", httpMethod = "GET")
    @RequestMapping(value = "/getApprover")
    public BaseResponse getApprover(Integer approverLevel) {
        BaseResponse response = ResponseUtils.setResultSuccess(getService().getApprover(approverLevel));
        return response;
    }
}
