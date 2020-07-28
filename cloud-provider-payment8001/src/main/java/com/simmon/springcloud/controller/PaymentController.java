package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/28 11:39
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public ResponseResult create(Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:{}", i);
        return new ResponseResult(200, "success");
    }

    @GetMapping("/payment/{id}")
    public ResponseResult getById(@PathVariable Long id) {
        Payment payment = paymentService.selectById(id);
        log.info("查询结果:{}", payment);
        return new ResponseResult(200, "success",payment);
    }
}
