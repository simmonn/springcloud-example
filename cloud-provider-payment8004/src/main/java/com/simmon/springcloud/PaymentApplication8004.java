package com.simmon.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/30 17:58
 */
@SpringBootApplication
//该注解用于像使用consul或者Zookeeper作为注册中心时注册服务
@EnableDiscoveryClient
public class PaymentApplication8004 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8004.class, args);
    }
}
