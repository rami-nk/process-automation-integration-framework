package io.miragon.servicetaskapi;

import io.miragon.servicetaskapi.impl.WorkerSubscription;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Camunda7WorkerAutoConfiguration {

    @Bean
    public WorkerSubscription camunda7WorkerAdapter(final ExternalTaskClient externalTaskClient, final Camunda7PojoMapper camunda7PojoMapper) {
        return new Camunda7WorkerAdapter(externalTaskClient, camunda7PojoMapper);
    }

    @Bean
    public Camunda7PojoMapper camunda7PojoMapper() {
        return new Camunda7PojoMapper();
    }
}