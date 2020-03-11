package com.mk.approve.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 张庆贺
 * @since 2019/5/29 22:30
 */
@Table(name = "t_user_info")
@Data
public class DemoUserInfo {
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "company")
    private String company;
}
