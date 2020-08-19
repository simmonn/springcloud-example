package com.simmon.springcloud.service;

import com.simmon.springcloud.dao.PaymentDao;
import com.simmon.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/28 11:31
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment selectById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
