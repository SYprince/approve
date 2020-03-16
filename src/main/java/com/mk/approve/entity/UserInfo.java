package com.mk.approve.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 张庆贺
 * @since 2019/5/29 22:30
 */
@Table(name = "user_info")
@Data
public class UserInfo {
    @Id
    //@Column(name = "user_id")
    private Integer userId;

    //@Column(name = "user_name")
    private String userName;

    private String account;
    private String openid;

    private String companyId;

    private String tel;

    private Integer identityType;
    @JsonIgnore
    private Integer delFlag;
}
