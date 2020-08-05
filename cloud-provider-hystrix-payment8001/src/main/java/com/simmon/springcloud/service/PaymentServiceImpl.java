package com.simmon.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.simmon.springcloud.dao.PaymentDao;
import com.simmon.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/28 11:31
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    /**
     * @param payment
     * @return
     */
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    /**
     * 正常访问
     * @param id
     * @return
     */
    @Override
    public Payment selectById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    /**
     * 超时访问
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public Payment selectByIdTimeout(Long id) {
//        int n = 1/0;
        try {
            TimeUnit.SECONDS.sleep(id+2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentDao.getPaymentById(id);
    }

    /**
     * 服务降级的返回值和参数要和服务正常调用的保持一致
     * @param id
     * @return
     */
    public Payment paymentTimeoutHandler(Long id){
        Payment payment = new Payment();
        payment.setId(id);
        payment.setSerial("服务降级,超时处理");
        return payment;
    }
}
