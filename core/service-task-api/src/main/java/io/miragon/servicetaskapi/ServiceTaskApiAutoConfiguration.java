package io.miragon.servicetaskapi;

import io.miragon.servicetaskapi.impl.WorkerAnnotationBeanProcessor;
import io.miragon.servicetaskapi.impl.WorkerRegistry;
import io.miragon.servicetaskapi.impl.WorkerRegistration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceTaskApiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WorkerRegistry workerRegistry(final WorkerRegistration workerRegistration) {
        return new WorkerRegistry(workerRegistration);
    }

    @Bean
    public WorkerAnnotationBeanProcessor workerAnnotationBeanProcessor(final WorkerRegistry workerRegistry) {
        return new WorkerAnnotationBeanProcessor(workerRegistry);
    }
}