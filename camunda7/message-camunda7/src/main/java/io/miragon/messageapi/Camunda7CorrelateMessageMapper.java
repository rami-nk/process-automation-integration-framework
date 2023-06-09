package io.miragon.messageapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.miragon.messageapi.api.CorrelateMessageCommand;
import lombok.AllArgsConstructor;
import org.camunda.community.rest.client.dto.CorrelationMessageDto;

@AllArgsConstructor
public class Camunda7CorrelateMessageMapper {

    private final Camunda7RestValueMapper camunda7BaseVariableValueMapper;

    public CorrelationMessageDto map(final CorrelateMessageCommand command) throws JsonProcessingException {

        final CorrelationMessageDto correlationMessageDto = new CorrelationMessageDto();
        correlationMessageDto.setMessageName(command.getMessageName());

        correlationMessageDto.setProcessInstanceId(command.getProcessInstanceId());
        correlationMessageDto.setProcessVariables(this.camunda7BaseVariableValueMapper.map(command.getData()));
        return correlationMessageDto;
    }
}
