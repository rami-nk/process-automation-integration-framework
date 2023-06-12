package io.miragon.servicetaskapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.servicetaskapi.impl.WorkerInfo;
import org.flowable.engine.delegate.JavaDelegate;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JavaDelegateFactory {

    public JavaDelegate createDelegate(WorkerInfo workerInfo) {
        return execution -> {
            try {
                var data = mapInput(workerInfo.getInputType(), execution.getVariables());
                var result = workerInfo.getMethod().invoke(workerInfo.getInstance(), data);
                execution.setVariables(mapOutput(result));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private Object mapInput(final Class<?> inputType, final Object object) {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.convertValue(object, inputType);
    }

    private Map<String, Object> mapOutput(final Object output) {
        if (Objects.isNull(output)) {
            return new HashMap<>();
        }

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(output, new TypeReference<>() {
        });
    }
}