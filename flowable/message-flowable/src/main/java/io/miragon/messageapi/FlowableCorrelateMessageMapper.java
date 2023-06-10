package io.miragon.messageapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.messageapi.api.CorrelateMessageCommand;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class FlowableCorrelateMessageMapper {

    private final ObjectMapper objectMapper;

    public Map<String, Object> map(final CorrelateMessageCommand command) {
        return objectMapper.convertValue(command.getData(), new TypeReference<>() {
        });
    }
}
