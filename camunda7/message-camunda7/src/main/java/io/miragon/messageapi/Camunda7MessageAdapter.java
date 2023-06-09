package io.miragon.messageapi;

import io.miragon.servicetaskapi.api.CorrelateMessageCommand;
import io.miragon.servicetaskapi.api.MessageCorrelationException;
import lombok.RequiredArgsConstructor;
import org.camunda.community.rest.client.api.MessageApi;
import org.camunda.community.rest.client.dto.CorrelationMessageDto;

@RequiredArgsConstructor
public class Camunda7MessageAdapter implements io.miragon.servicetaskapi.api.MessageApi {

    private final Camunda7CorrelateMessageMapper mapper;
    private final MessageApi messageApi;

    @Override
    public void correlateMessage(final CorrelateMessageCommand command) throws MessageCorrelationException {
        try {
            final CorrelationMessageDto dto = this.mapper.map(command);
            this.messageApi.deliverMessage(dto);
        } catch (final Exception e) {
            throw new MessageCorrelationException("Message correlation failed.", e);
        }
    }
}