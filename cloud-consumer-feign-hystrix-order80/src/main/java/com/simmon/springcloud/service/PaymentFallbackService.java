package com.simmon.springcloud.service;

import com.simmon.springcloud.entities.Payment;
import com.simmon.springcloud.entities.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/8/5 18:27
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public ResponseResult getById(Long id) {
        Payment payment = new Payment();
        payment.setSerial("---------PaymentFallbackService-----ok----");
        return new ResponseResult(200, "success", payment);
    }

    @Override
    public ResponseResult getByIdTimeoutt(Long id) {
        Payment payment = new Payment();
        payment.setSerial("---------PaymentFallbackService----timeout------");
        return new ResponseResult(502, "timeout", payment);
    }
}
