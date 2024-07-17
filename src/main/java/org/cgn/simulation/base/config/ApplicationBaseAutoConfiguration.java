package org.cgn.simulation.base.config;

import org.cgn.simulation.base.ApplicationContextHolder;
import org.cgn.simulation.base.init.ApplicationContentPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBaseAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder simulationApplicationContextHolder() {
        System.out.println("Creating simulationApplicationContextHolder bean...");
        return new ApplicationContextHolder();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContentPostProcessor simulationApplicationContentPostProcessor(ApplicationContext applicationContext) {
        System.out.println("Creating simulationApplicationContentPostProcessor bean...");
        return new ApplicationContentPostProcessor(applicationContext);
    }


}
