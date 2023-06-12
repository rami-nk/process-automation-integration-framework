package io.miragon.servicetaskapi.impl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkerRegistry {

    private final WorkerRegistration workerRegistration;

    public void register(final WorkerInfo workerInfo) {
        workerRegistration.register(workerInfo);
    }
}