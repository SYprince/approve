package com.mk.approve.service.impl;

import com.mk.approve.base.service.BaseServiceImpl;
import com.mk.approve.entity.DemoUserInfo;
import com.mk.approve.mapper.UserInfoMapper;
import com.mk.approve.service.IUserInfoBaseService;
import org.springframework.stereotype.Service;

/**
 * @author 张庆贺
 * @since 2020/2/22 12:56
 */
@Service
public class UserInfoBaseServiceImpl extends BaseServiceImpl<UserInfoMapper, DemoUserInfo> implements IUserInfoBaseService {
}
