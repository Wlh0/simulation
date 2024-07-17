package org.cgn.simulation.base.designpattern.config;

import org.cgn.simulation.base.config.ApplicationBaseAutoConfiguration;
import org.cgn.simulation.base.designpattern.chain.AbstractChainContext;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ImportAutoConfiguration(ApplicationBaseAutoConfiguration.class)
public class DesignPatternAutoConfiguration {

    @Bean
    public AbstractChainContext abstractChainContext() {
        System.out.println("Creating AbstractChainContext bean...");
        return new AbstractChainContext();
    }
}
