package io.miragon.servicetaskapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ClassUtils;
import org.camunda.bpm.client.variable.impl.value.JsonValueImpl;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.TypedValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Camunda7PojoMapper {

    public VariableMap mapToEngineData(final Map<String, Object> data) throws JsonProcessingException {
        final VariableMap variables = Variables.createVariables();
        for (String key : data.keySet()) {
            final Object value = data.get(key);
            if (Objects.isNull(value) || ClassUtils.isPrimitiveOrWrapper(value.getClass()) || value instanceof String) {
                variables.putValue(key, value);
            } else {
                final ObjectMapper objectMapper = new ObjectMapper();
                final JsonValueImpl json = new JsonValueImpl(objectMapper.writeValueAsString(value));
                variables.putValueTyped(key, json);
            }
        }
        return variables;
    }

    public Map<String, Object> mapFromEngineData(final VariableMap variables) {
        final Map<String, Object> data = new HashMap<>();
        variables.keySet().forEach(key -> {
            final TypedValue value = variables.getValueTyped(key);
            if (value.getType().getName().equals("json")) {
                try {
                    data.put(key, this.mapFromEngineData(value.getValue()));
                } catch (final JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            } else {
                data.put(key, value.getValue());
            }
        });
        return data;
    }

    public Object mapFromEngineData(final Object value) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        if (value.toString().startsWith("[")) {
            return mapper.readValue(value.toString(), new TypeReference<List<?>>() {
            });
        }
        return mapper.readValue(value.toString(), new TypeReference<Map<String, Object>>() {
        });
    }
}