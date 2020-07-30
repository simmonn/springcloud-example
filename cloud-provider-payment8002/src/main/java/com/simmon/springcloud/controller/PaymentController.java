package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public ResponseResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:{},port:{}", i,serverPort);
        return new ResponseResult(200, "success");
    }

    @GetMapping("/payment/{id}")
    public ResponseResult getById(@PathVariable Long id) {
        Payment payment = paymentService.selectById(id);
        log.info("查询结果:{}.port:{}", payment,serverPort);
        return new ResponseResult(200, "success",payment);
    }
}