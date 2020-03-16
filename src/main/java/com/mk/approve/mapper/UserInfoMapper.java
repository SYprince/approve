package com.mk.approve.mapper;





import com.mk.approve.base.mapper.BaseMapper;
import com.mk.approve.entity.UserInfo;

import java.util.List;

/**
 * @author 张庆贺
 * @since 2019/6/13 17:42
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 查询用户列表
     * @return
     */
    List<UserInfo> findAllUser();
}
