package com.mk.approve.controller;

import com.mk.approve.base.controller.BaseController;
import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.exception.BusinessException;
import com.mk.approve.base.utils.ResponseUtils;
import com.mk.approve.entity.DemoUserInfo;
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
 * @author 张庆贺getUserInfoById
 * @since 2019/5/30 11:38
 */
@RestController
@RequestMapping("/demoUserController")
@Slf4j
@Api(value = "demo接口")
public class DemoUserController extends BaseController<IUserInfoBaseService, DemoUserInfo> {

    @ApiOperation(value = "根据ID查询用户", notes = "根据ID查询用户", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户ID", dataType = "Integer", paramType = "query", required = true)})
    @RequestMapping(value = "/getById")
    public BaseResponse getObject(@RequestParam() Integer userId) {
        log.info("收到消息，getById");
        DemoUserInfo demoUserInfo = new DemoUserInfo();
        demoUserInfo.setUserId(userId);
        return ResponseUtils.setResultSuccess(getService().selectOne(demoUserInfo));
    }
}
