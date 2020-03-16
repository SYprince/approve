package com.mk.approve.response;

import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 9:38 2020/3/12
 */
@Getter
@Setter
public class LoginResponse {

    private UserInfo userInfo;

    private List<SteelDamageInfo> steelFlowList;
}
