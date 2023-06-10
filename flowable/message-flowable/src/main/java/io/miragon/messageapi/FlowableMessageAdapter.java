package io.miragon.messageapi;

import io.miragon.messageapi.api.CorrelateMessageCommand;
import io.miragon.messageapi.api.MessageApi;
import io.miragon.messageapi.api.MessageCorrelationException;
import lombok.RequiredArgsConstructor;
import org.flowable.engine.RuntimeService;

@RequiredArgsConstructor
public class FlowableMessageAdapter implements MessageApi {

    private final RuntimeService runtimeService;

    private final FlowableCorrelateMessageMapper mapper;

    @Override
    public void correlateMessage(final CorrelateMessageCommand command) throws MessageCorrelationException {

        var variables = mapper.map(command);

        // get the first execution with the message event subscription
        var execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("BookRequestConfirmation")
                .processInstanceId(command.getProcessInstanceId())
                .singleResult();

        try {
            runtimeService.messageEventReceived("BookRequestConfirmation", execution.getId(), variables);
        } catch (final Exception e) {
            throw new MessageCorrelationException("Message correlation failed.", e);
        }
    }
}