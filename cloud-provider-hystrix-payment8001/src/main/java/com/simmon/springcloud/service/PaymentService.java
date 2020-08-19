package com.simmon.springcloud.service;


import com.simmon.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/28 11:30
 */
public interface PaymentService {

    int create(Payment payment);

    Payment selectById(Long id);

    Payment selectByIdTimeout(Long id);

    public String payCircuitBreaker(@PathVariable Integer id);
}
