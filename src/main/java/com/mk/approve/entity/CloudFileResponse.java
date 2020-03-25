package com.mk.approve.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class CloudFileResponse {

    Integer errcode;

    String errmsg;

    List file_list;
}
