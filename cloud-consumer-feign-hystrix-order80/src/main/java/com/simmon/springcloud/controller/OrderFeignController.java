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
/*    @HystrixCommand(fallbackMethod = "paymentFeignTimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1000")
    })*/
    public ResponseResult<Payment> paymentFeignTimeout(@PathVariable Long id){
        //默认超时时间为1秒
        return paymentHystrixService.getByIdTimeoutt(id);
    }

    public ResponseResult<Payment> paymentFeignTimeoutHandler(@PathVariable Long id) {
        Payment payment = new Payment();
        payment.setSerial("调用出错,服务降级");
        return new ResponseResult<Payment>(200,"服务降级",payment);
    }

}
