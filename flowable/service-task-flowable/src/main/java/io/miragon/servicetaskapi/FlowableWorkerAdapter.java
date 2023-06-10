package io.miragon.servicetaskapi;

import io.miragon.servicetaskapi.impl.WorkerInfo;
import io.miragon.servicetaskapi.impl.WorkerSubscription;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlowableWorkerAdapter implements WorkerSubscription {

    private final JavaDelegateRegistrator javaDelegateRegistrator;

    private final JavaDelegateFactory javaDelegateFactory;

    @Override
    public void subscribe(WorkerInfo workerInfo) {
        var delegate = javaDelegateFactory.createDelegate(workerInfo);
        javaDelegateRegistrator.register(delegate, workerInfo.getType());
    }
}