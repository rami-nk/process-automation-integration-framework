package io.miragon.servicetaskapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.miragon.servicetaskapi.impl.WorkerInfo;
import io.miragon.servicetaskapi.impl.WorkerSubscription;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class Camunda7WorkerAdapter implements WorkerSubscription {

    private final ExternalTaskClient externalTaskClient;

    private final Camunda7PojoMapper camunda7PojoMapper;

    @Override
    public void subscribe(WorkerInfo workerInfo) {
        this.externalTaskClient.subscribe(workerInfo.getType())
                .lockDuration(30000L)
                .handler((externalTask, externalTaskService) ->
                        this.execute(externalTask, externalTaskService, workerInfo)
                )
                .open();
    }

    private void execute(ExternalTask externalTask, ExternalTaskService service, WorkerInfo workerInfo) {
        final Map<String, Object> data = camunda7PojoMapper.mapFromEngineData(externalTask.getAllVariablesTyped());
        try {
            final Object mappedInput = mapInput(workerInfo.getInputType(), data);
            final Object result = workerInfo.getMethod().invoke(workerInfo.getInstance(), mappedInput);
            final Map<String, Object> output = mapOutput(result);
            service.complete(externalTask, null, camunda7PojoMapper.mapToEngineData(output));
        } catch (IllegalAccessException | InvocationTargetException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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