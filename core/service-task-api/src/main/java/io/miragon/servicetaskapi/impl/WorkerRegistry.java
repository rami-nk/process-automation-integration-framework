package io.miragon.servicetaskapi.impl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkerRegistry {

    private final WorkerSubscription workerSubscription;

    public void register(final WorkerInfo workerInfo) {
        workerSubscription.subscribe(workerInfo);
    }
}