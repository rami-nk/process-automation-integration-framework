package io.miragon.messageapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.miragon.servicetaskapi.api.CorrelateMessageCommand;
import lombok.AllArgsConstructor;
import org.camunda.community.rest.client.dto.CorrelationMessageDto;
import org.camunda.community.rest.client.dto.VariableValueDto;

import java.util.Map;

@AllArgsConstructor
public class Camunda7CorrelateMessageMapper {

    private final Camunda7RestValueMapper camunda7BaseVariableValueMapper;

    public CorrelationMessageDto map(final CorrelateMessageCommand command) throws JsonProcessingException {

        final CorrelationMessageDto correlationMessageDto = new CorrelationMessageDto();
        correlationMessageDto.setMessageName(command.getMessageName());

        final VariableValueDto correlationKey = this.camunda7BaseVariableValueMapper.createValue(command.getCorrelationKey());
        correlationMessageDto.setLocalCorrelationKeys(Map.of("correlationKey", correlationKey));
        correlationMessageDto.setProcessVariables(this.camunda7BaseVariableValueMapper.map(command.getData()));
        return correlationMessageDto;
    }
}
