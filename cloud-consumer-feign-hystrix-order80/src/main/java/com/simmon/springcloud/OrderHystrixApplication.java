package com.simmon.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/29 17:59
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = RibbonRule.class)
public class OrderHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApplication.class, args);
    }
}
