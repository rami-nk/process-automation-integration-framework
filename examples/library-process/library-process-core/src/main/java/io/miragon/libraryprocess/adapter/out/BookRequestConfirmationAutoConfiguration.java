package io.miragon.libraryprocess.adapter.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationPort;
import io.miragon.messageapi.api.MessageApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookRequestConfirmationAutoConfiguration {

    @Bean
    public CustomerBookRequestConfirmationPort customerMessagePort(final MessageApi messageApi, final ObjectMapper mapper) {
        return new BookRequestConfirmationAdapter(messageApi, mapper);
    }
}