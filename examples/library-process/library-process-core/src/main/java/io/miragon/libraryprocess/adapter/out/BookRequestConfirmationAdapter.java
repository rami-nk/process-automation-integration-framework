package io.miragon.libraryprocess.adapter.out;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationCommand;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationPort;
import io.miragon.messageapi.api.CorrelateMessageCommand;
import io.miragon.messageapi.api.MessageApi;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class BookRequestConfirmationAdapter implements CustomerBookRequestConfirmationPort {

    private final MessageApi messageApi;

    private final ObjectMapper objectMapper;

    @Override
    public void confirmBookRequest(CustomerBookRequestConfirmationCommand customerBookRequestConfirmationCommand) {
        final Map<String, Object> data =  objectMapper.convertValue(customerBookRequestConfirmationCommand, new TypeReference<>() {});
        messageApi.correlateMessage( new CorrelateMessageCommand("BookRequestConfirmation", customerBookRequestConfirmationCommand.getCustomerId(), data));
    }
}