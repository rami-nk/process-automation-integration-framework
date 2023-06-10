package io.miragon.libraryprocess.adapter.in.worker;

import io.miragon.libraryprocess.application.port.in.checkbookavailablity.CheckBookAvailabilityUseCase;
import io.miragon.libraryprocess.application.port.in.prepareshipment.PrepareShipmentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerAutoConfiguration {

    @Bean
    public WorkerAdapter workerAdapter(final CheckBookAvailabilityUseCase checkBookAvailabilityUseCase, final PrepareShipmentUseCase prepareShipmentUseCase) {
        return new WorkerAdapter(checkBookAvailabilityUseCase, prepareShipmentUseCase);
    }
}