package com.simmon.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: simmon
 * @description: TODO
 * @date: 2020/7/29 18:11
 */
@Configuration
public class ApplicationConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
