package com.mk.approve.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 12:03 2020/3/22
 */
@Getter
@Setter
public class CloudFileRequest {

    public CloudFileRequest(){
        this.env = "test-ck8iu";
    }

    String env ;

    List<File> file_list;


}
