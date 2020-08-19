package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @PostMapping("/payment/create")
    public ResponseResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        log.info("插入结果:{},port:{}", i,serverPort);
        return new ResponseResult(200, "success");
    }

    @GetMapping("/payment/{id}")
    public ResponseResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.selectById(id);
        log.info("查询结果:{}.port:{}", payment,serverPort);
        return new ResponseResult(200, "端口:"+serverPort,payment);
    }

    @GetMapping("/payment/discovery")
    public void discoveries(){
        List<String> services = discoveryClient.getServices();
        services.forEach(service->{
            log.info(service);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(instance->{
            log.info(instance.getInstanceId()+instance.getHost()+instance.getPort()+instance.getUri());
        });
    }

    @GetMapping("/payment/lb")
    public String lb(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
