package com.simmon.springcloud.dao;

import com.simmon.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/27 18:28
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
