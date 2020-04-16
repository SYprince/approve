package com.mk.approve.controller;

import com.mk.approve.base.controller.BaseController;
import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.utils.ResponseUtils;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.service.SteelDamageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * version:1.0.0
 * @Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 20:37 2020/3/12
 */
@RestController
@RequestMapping("/steelDamageController")
@Slf4j
@Api(value = "重伤钢轨信息相关接口")
public class SteelDamageController extends BaseController<SteelDamageInfoService, SteelDamageInfo> {


    @ApiOperation(value = "获取重伤钢轨信息列表", notes = "获取重伤钢轨信息列表（登陆后列表页初始化访问此接口），根据账号和登录接口返回的账号身份类型", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "账号", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "identityType", value = "身份类型", dataType = "Integer", paramType = "query", required = true)
    })
    @RequestMapping(value = "/getSteelDamageInfoList")
    public BaseResponse getSteelDamageInfoList(String account, Integer identityType) {

        return ResponseUtils.setResultSuccess(getService().getSteelDamageInfoList(account,identityType));
    }

    @ApiOperation(value = "获取重伤钢轨信息详情", notes = "获取重伤钢轨信息详情", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "steelId", value = "钢轨存单信息流程id", dataType = "String", paramType = "query", required = true)
    })
    @RequestMapping(value = "/getSteelDetail")
    public BaseResponse getSteelDetail(String steelId) {
        System.out.println("");
        return ResponseUtils.setResultSuccess(getService().getSteelDetail(Integer.valueOf(steelId)));
    }

    @ApiOperation(value = "审批接口 根据id修改存单状态", notes = "根据id修改存单状态", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "steelId", value = "钢轨存单信息流程id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "flowState", value = "更新状态值", dataType = "String", paramType = "query", required = true)
    })
    @RequestMapping(value = "/steelInfoApprove")
    public BaseResponse steelInfoApprove(String steelId,Integer flowState,Integer secondaryApproverId,String handwritingsignUrl ,Integer identityType) {

        return ResponseUtils.setResultSuccess(getService().steelInfoApprove(steelId,flowState,secondaryApproverId,handwritingsignUrl,identityType));
    }

    @ApiOperation(value = "新增重伤钢轨信息（发起流转流程）", notes = "新增重伤钢轨信息", httpMethod = "POST")
    @RequestMapping(value = "/addSteelDamageInfo")
    public BaseResponse addSteelDamageInfo(@RequestBody SteelDamageInfo steelDamageInfo) {

        return ResponseUtils.setResultSuccess(getService().addSteelDamageInfo(steelDamageInfo));
    }

    @ApiOperation(value = "下载存单按钮", notes = "根据云存储图片fileid请求获取下载url-根据url下载图片/file/img-生成excel再存服务器", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "steelId", value = "钢轨存单信息流程id", dataType = "String", paramType = "query", required = true)
    })
    @RequestMapping(value = "/downloadFile",method = RequestMethod.POST)
    public BaseResponse downloadFile(String steelId)  throws Exception {

        return ResponseUtils.setResultSuccess(getService().uploadCloudPicture(Integer.valueOf(steelId)));
    }
}
