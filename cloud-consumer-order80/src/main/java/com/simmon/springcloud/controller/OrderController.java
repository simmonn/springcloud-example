package com.simmon.springcloud.controller;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import com.simmon.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/29 18:02
 */
@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/create")
    public ResponseResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public ResponseResult<Payment> getPayment(@PathVariable Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, ResponseResult.class);
    }

    @GetMapping("/consumer/payment/entity/{id}")
    public ResponseResult<Payment> getPaymentEntity(@PathVariable Long id) {
        ResponseEntity<ResponseResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, ResponseResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new ResponseResult<>(500, "操作失败");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances.isEmpty()) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb" , String.class);
    }



}
