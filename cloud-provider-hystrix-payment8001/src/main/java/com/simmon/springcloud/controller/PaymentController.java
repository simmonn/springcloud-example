package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/hystrix/create")
    public ResponseResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:{},port:{}", i,serverPort);
        return new ResponseResult(200, "success");
    }

    @GetMapping("/payment/hystrix/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectById(id);
        log.info("查询结果:{}.port:{}", payment,serverPort);
        return new ResponseResult(200, "端口:"+serverPort,payment);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public ResponseResult getByIdTimeout(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectByIdTimeout(id);
        log.info("查询结果:{}.port:{}", payment,serverPort);
        return new ResponseResult(200, "端口:"+serverPort,payment);
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentZCircuit(@PathVariable Integer id) {
        return paymentService.payCircuitBreaker(id);
    }

}
