package com.mk.approve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.mk.approve.mapper")
@EnableSwagger2
public class ApproveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApproveApplication.class, args);
    }
}
