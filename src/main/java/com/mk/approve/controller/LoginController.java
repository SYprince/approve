package com.mk.approve.controller;

import com.mk.approve.base.controller.BaseController;
import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.utils.ResponseUtils;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.entity.UserInfo;
import com.mk.approve.mapper.SteelDamageInfoMapper;
import com.mk.approve.response.LoginResponse;
import com.mk.approve.service.IUserInfoBaseService;
import com.mk.approve.service.SteelDamageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author prince
 * @since 2020/3/12 11:38
 */
@RestController
@RequestMapping("/loginController")
@Slf4j
@Api(value = "登陆相关接口")
public class LoginController extends BaseController<IUserInfoBaseService, UserInfo> {

    @Autowired
    SteelDamageInfoService steelDamageInfoService;

    @ApiOperation(value = "登录接口", notes = "根据手机号（账号）查询返回身份类型", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "account", value = "账号", dataType = "String", paramType = "query", required = true)})
    @RequestMapping(value = "/login")
    public BaseResponse login(@RequestParam() String account) {
        //log.info("收到消息，getById");
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(account);
        userInfo.setDelFlag(0);
        UserInfo loginUser = getService().selectOne(userInfo);

        //直接返回用户信息
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserInfo(loginUser);
        //loginResponse.setSteelFlowList(steelDamageInfoService.getSteelListByPromoterId(loginUser.getUserId()));
        return ResponseUtils.setResultSuccess(loginResponse);
    }
}
