package io.miragon.messageapi.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorrelateMessageCommand {

    private String messageName;

    private String businessKey;

    private String correlationKey;

    private Map<String, Object> data;
}