package com.mk.approve.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Description: 重伤钢轨信息 暂时和
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 14:35 2020/3/12
 */
@Table(name = "steel_damage_info")
@Data
public class SteelDamageInfo {

    @Id
    @Column(name = "steel_id")
    private Integer steelId;

    private String steelFlowName;
    private Integer flowState;

    private Integer promoterId;
    private Integer firstApproverId;
    private Integer secondaryApproverId;

    private String discoveryTime;
    private String jobDifferent;
    private String lineName;
    private String mileage;
    private String gubie;
    private String ironNumber;
    private String curvedLine;
    private String factoryName;

    private String instrumentNumber;
    private String displayWave;

    private String steelLength;
    private String steelType;
    //表格里手填的日期
    private String steelDate;

    /**
     * 日期
     */
    private String steelFlowDate;

    /**
     * 折损原因
     */
    private String damageReason;
    /**
     * 伤损位置
     */
    private String damageLocation;
    /**
     * 伤损现状
     */
    private String damageSituation;

    private String remark;
    /**
     * 示意图在 云存储的url
     */
    private String sketchUrl;

    private String zhiji;
    private String zhushou;
    private String lingban;
    //发起人填的-处理意见
    private String handleSuggestion;
    /**
     * 手写签名 云存储图片url  一级审批人签名
     */
    private String handwritingsignUrl1;
    /**
     * 手写签名 云存储图片url  二级审批人签名
     */
    private String handwritingsignUrl2;

    @JsonIgnore
    private Integer delFlag;

    /**
     * 流程列表展示 环节人名
     */
    @Transient
    private String promoterName;
    @Transient
    private String firstApproverName;
    @Transient
    private String secondaryApproverName;
}
