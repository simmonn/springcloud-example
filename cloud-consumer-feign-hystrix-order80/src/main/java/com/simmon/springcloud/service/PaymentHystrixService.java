package com.simmon.springcloud.service;

import com.simmon.springcloud.entities.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/8/3 23:05
 */
@Component
@FeignClient(value = "cloud-payment-hystrix-service", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/{id}")
    public ResponseResult getById(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public ResponseResult getByIdTimeoutt(@PathVariable("id") Long id);

}
