package com.simmon.springcloud.service;


import com.simmon.entities.Payment;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/28 11:30
 */
public interface PaymentService {

    int create(Payment payment);

    Payment selectById(Long id);
}
