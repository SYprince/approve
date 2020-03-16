package com.mk.approve.service;


import com.mk.approve.base.service.IBaseService;
import com.mk.approve.entity.UserInfo;

import java.util.List;

/**
 * @author 张庆贺
 * @since 2020/2/22 11:31
 */
public interface IUserInfoBaseService extends IBaseService<UserInfo> {

     List<UserInfo> getApprover(Integer approverLevel);
}
