package io.miragon.servicetaskapi;

import io.miragon.servicetaskapi.impl.WorkerInfo;
import io.miragon.servicetaskapi.impl.WorkerRegistration;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FlowableWorkerAdapter implements WorkerRegistration {

    private final JavaDelegateRegistrator javaDelegateRegistrator;

    private final JavaDelegateFactory javaDelegateFactory;

    @Override
    public void register(WorkerInfo workerInfo) {
        var delegate = javaDelegateFactory.createDelegate(workerInfo);
        javaDelegateRegistrator.register(delegate, workerInfo.getType());
    }
}