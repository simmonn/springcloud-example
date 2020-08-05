package com.simmon.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/27 18:15
 */
@SpringBootApplication
@EnableEurekaClient
//回路
@EnableCircuitBreaker
public class PaymentApplicationHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationHystrix8001.class, args);
    }
}
