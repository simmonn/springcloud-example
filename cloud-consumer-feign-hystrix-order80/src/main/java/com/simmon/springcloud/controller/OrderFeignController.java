package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/29 18:02
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    public PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/{id}")
    public ResponseResult<Payment> getPayment(@PathVariable Long id) {
        return paymentHystrixService.getById(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public ResponseResult<Payment> paymentFeignTimeout(@PathVariable Long id){
        //默认超时时间为1秒
        return paymentHystrixService.getByIdTimeoutt(id);
    }

}
