package com.aaa.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
* @Author: zcl
* @ClassName: AppRunEureka6081
* @Description:
* @Date: 2020/7/1 20:30
*/
@SpringBootApplication
@EnableEurekaServer
public class AppRunEureka6082 {
    public static void main(String[] args) {
        SpringApplication.run(AppRunEureka6082.class,args);
    }
}
