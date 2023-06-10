package io.miragon.messageapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flowable.engine.RuntimeService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
public class FlowableMessageAutoConfiguration {

    @Bean
    public FlowableMessageAdapter camunda7MessageAdapter(final RuntimeService runtimeService, final FlowableCorrelateMessageMapper correlateMessageMapper) {
        return new FlowableMessageAdapter(runtimeService, correlateMessageMapper);
    }

    @Bean
    public FlowableCorrelateMessageMapper correlateMessageMapper(final ObjectMapper objectMapper) {
        return new FlowableCorrelateMessageMapper(objectMapper);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}