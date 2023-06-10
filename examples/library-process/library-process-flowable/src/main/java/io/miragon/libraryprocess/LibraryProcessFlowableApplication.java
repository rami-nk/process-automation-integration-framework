package io.miragon.libraryprocess;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Import(LibraryProcessAutoConfiguration.class)
@Slf4j
public class LibraryProcessFlowableApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(LibraryProcessFlowableApplication.class, args);

        var repositoryService = context.getBean(RepositoryService.class);
        var runtimeService = context.getBean(RuntimeService.class);

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("book-request.bpmn20.xml")
                .deploy();

        log.info("Deployed: {}", deployment.getId());

        Map<String, Object> variables = new HashMap<>();
        variables.put("title", "Harry Potter");
        variables.put("author", "J.K. Rowling");
        variables.put("isbn", "978-3-16-148410-0");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("book-request", variables);

        log.info("Process \"{}\" started", processInstance.getId());
    }
}