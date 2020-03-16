package com.mk.approve.service.impl;

import com.mk.approve.base.service.BaseServiceImpl;
import com.mk.approve.entity.UserInfo;
import com.mk.approve.mapper.UserInfoMapper;
import com.mk.approve.service.IUserInfoBaseService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张庆贺
 * @since 2020/2/22 12:56
 */
@Service
public class UserInfoBaseServiceImpl extends BaseServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoBaseService {

    /**
     * 获取全部某一级审批人信息（一级2、二级3）
     * @return
     */
    @Override
    public List<UserInfo> getApprover(Integer approverLevel){
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("delFlag",0);
        if(approverLevel == null){
            //不传参 查一二级 审批人
            criteria.andIn("identityType",Arrays.asList(2,3));
        }else {
            criteria.andEqualTo("identityType",approverLevel);
        }

        return this.baseMapper.selectByExample(example);
    }

}
