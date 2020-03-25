package com.mk.approve.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "global_token")
@Data
public class GlobalToken {

    @Id
    @Column(name = "id")
    private Integer id;

    private String accessToken;
    private String updateTime;
}
