package com.mk.approve.service;

import com.mk.approve.base.service.IBaseService;
import com.mk.approve.entity.SteelDamageInfo;

import java.util.List;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 20:40 2020/3/12
 */
public interface SteelDamageInfoService extends IBaseService<SteelDamageInfo> {

    List<SteelDamageInfo> getSteelDamageInfoList(String account ,Integer identityType);

    SteelDamageInfo getSteelDetail(Integer steelId);

    int steelInfoApprove (String steelId,Integer flowState);

    int addSteelDamageInfo(SteelDamageInfo steelDamageInfo);
}
