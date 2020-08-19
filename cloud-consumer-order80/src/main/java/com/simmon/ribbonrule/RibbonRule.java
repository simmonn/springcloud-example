package com.simmon.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: simmon
 * @description: ribbon自定义规则
 * @date: 2020/7/31 16:37
 */
@Configuration
public class RibbonRule {

    @Bean(name = "myRibbonRule")
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
