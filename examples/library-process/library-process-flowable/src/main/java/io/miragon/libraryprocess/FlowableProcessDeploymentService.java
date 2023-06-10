package io.miragon.libraryprocess;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

//@Service
@AllArgsConstructor
@Slf4j
public class FlowableProcessDeploymentService {

    private final RepositoryService repositoryService;

    private final RuntimeService runtimeService;

    private final ConfigurableApplicationContext applicationContext;

    @PostConstruct
    public void deployProcess() {
    }
}