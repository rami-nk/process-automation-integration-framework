package io.miragon.servicetaskapi.api;

public interface MessageApi {
    void correlateMessage(CorrelateMessageCommand command);
}