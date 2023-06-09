package io.miragon.servicetaskapi.api;

public class MessageCorrelationException extends RuntimeException {

    public MessageCorrelationException(final String message) {
        super(message);
    }

    public MessageCorrelationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}