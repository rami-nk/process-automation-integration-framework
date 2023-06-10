package io.miragon.messageapi.api;

public interface MessageApi {
    void correlateMessage(CorrelateMessageCommand command);
}