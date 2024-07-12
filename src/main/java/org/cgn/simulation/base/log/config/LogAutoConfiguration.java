package org.cgn.simulation.base.log.config;

import org.cgn.simulation.base.log.core.ILogPrintAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动导入配置文件，将bean注入
 */
@Configuration
public class LogAutoConfiguration {
    @Bean
    public ILogPrintAspect iLogPrintAspect() {
        return new ILogPrintAspect();
    }

}
