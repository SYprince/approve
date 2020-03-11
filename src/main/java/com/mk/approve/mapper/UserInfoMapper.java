package com.mk.approve.mapper;





import com.mk.approve.base.mapper.BaseMapper;
import com.mk.approve.entity.DemoUserInfo;

import java.util.List;

/**
 * @author 张庆贺
 * @since 2019/6/13 17:42
 */
public interface UserInfoMapper extends BaseMapper<DemoUserInfo> {
    /**
     * 查询用户列表
     * @return
     */
    List<DemoUserInfo> findAllUser();
}
