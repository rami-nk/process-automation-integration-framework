package io.miragon.servicetaskapi;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
public class Camunda7WorkerAutoConfiguration {

    @Bean
    public Camunda7PojoMapper camunda7PojoMapper() {
        return new Camunda7PojoMapper();
    }

    @Bean
    public Camunda7WorkerAdapter camunda7WorkerAdapter(final ExternalTaskClient externalTaskClient, final Camunda7PojoMapper camunda7PojoMapper) {
        return new Camunda7WorkerAdapter(externalTaskClient, camunda7PojoMapper);
    }
}