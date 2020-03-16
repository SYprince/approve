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
