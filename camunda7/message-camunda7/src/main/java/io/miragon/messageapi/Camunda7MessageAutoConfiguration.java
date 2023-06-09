package io.miragon.messageapi;

import org.camunda.community.rest.client.api.MessageApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
public class Camunda7MessageAutoConfiguration {

    @Bean
    public Camunda7MessageAdapter camunda7MessageAdapter(final MessageApi messageApi, final Camunda7RestValueMapper camunda7BaseVariableValueMapper) {
        return new Camunda7MessageAdapter(new Camunda7CorrelateMessageMapper(camunda7BaseVariableValueMapper), messageApi);
    }
}