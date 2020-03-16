package com.mk.approve.service.impl;

import com.mk.approve.base.service.BaseServiceImpl;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.entity.UserInfo;
import com.mk.approve.mapper.SteelDamageInfoMapper;
import com.mk.approve.mapper.UserInfoMapper;
import com.mk.approve.service.IUserInfoBaseService;
import com.mk.approve.service.SteelDamageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * version:1.0.0
 * @Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 20:40 2020/3/12
 */
@Service
public class SteelDamageInfoServiceImpl extends BaseServiceImpl<SteelDamageInfoMapper, SteelDamageInfo> implements SteelDamageInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    SteelDamageInfoMapper steelDamageInfoMapper;

    /**
     * 查询列表信息
     * @param account
     * @param identityType 1发起人  2一级审批人  3二级审批人
     * @return
     */
    @Override
    public List<SteelDamageInfo> getSteelDamageInfoList(String account, Integer identityType) {
        //UserInfo userInfo = new UserInfo();
        //userInfo.setAccount(account);
        //userInfo.setDelFlag(0);
        ////
        //UserInfo loginUser = userInfoMapper.selectOne(userInfo);

        List<SteelDamageInfo> result = new ArrayList<>();
        if(1 == identityType){
            result = steelDamageInfoMapper.getSteelListByPromoterId(account);
        }else if(2 == identityType){
            result = steelDamageInfoMapper.getSteelListByFirstApproverId(account);
        }else if(3 == identityType){
            result = steelDamageInfoMapper.getSteelListBySecondaryApproverId(account);
        }

        return result;
    }

    @Override
    public SteelDamageInfo getSteelDetail(Integer steelId) {
        SteelDamageInfo steelDamageInfo = steelDamageInfoMapper.selectByPrimaryKey(steelId);
        Integer promoterId = steelDamageInfo.getPromoterId();
        Integer firstApproverId = steelDamageInfo.getFirstApproverId();
        Integer secondaryApproverId = steelDamageInfo.getSecondaryApproverId();
        if(promoterId != null){
            steelDamageInfo.setPromoterName(getUserNameByUserId(promoterId));
        }
        if(firstApproverId != null){
            steelDamageInfo.setFirstApproverName(getUserNameByUserId(firstApproverId));
        }
        if(secondaryApproverId != null){
            steelDamageInfo.setSecondaryApproverName(getUserNameByUserId(secondaryApproverId));
        }
        return steelDamageInfo;
    }

    private String getUserNameByUserId(Integer userId){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        return userInfo.getUserName();
    }

    /**
     * 更新 存单状态 1.发起 2.一级审批人 3.二级审批人 4.完成
     * @param steelId
     * @param flowState
     * @return
     */
    @Override
    public int steelInfoApprove(String steelId, Integer flowState) {
        SteelDamageInfo steelDamageInfo = new SteelDamageInfo();
        steelDamageInfo.setSteelId(Integer.valueOf(steelId));
        steelDamageInfo.setFlowState(flowState);
        return this.baseMapper.updateByPrimaryKeySelective(steelDamageInfo);
    }

    /**
     * 发起流程 添加钢轨信息
     * @param steelDamageInfo
     * @return
     */
    @Override
    public int addSteelDamageInfo(SteelDamageInfo steelDamageInfo) {
        //流程发起后 就到一级审批人 状态为2
        steelDamageInfo.setFlowState(2);
        steelDamageInfo.setDelFlag(0);
        return this.baseMapper.insertSelective(steelDamageInfo);
    }
}
