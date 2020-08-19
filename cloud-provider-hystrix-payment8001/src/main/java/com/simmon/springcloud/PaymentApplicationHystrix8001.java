package com.simmon.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/27 18:15
 */
//@SpringBootApplication
@EnableEurekaClient
//回路
@EnableCircuitBreaker
public class PaymentApplicationHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationHystrix8001.class, args);
    }

    /**
     * 此配置是为了服务监控而配置,与服务容错本身无关,SpringCloud升级后的坑
     * ServletRegistrationBean因为springboot的默认路径不是/hystrix.stream,
     * 只要在自己的项目配置上下面的servlet就可以了
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(streamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }
}