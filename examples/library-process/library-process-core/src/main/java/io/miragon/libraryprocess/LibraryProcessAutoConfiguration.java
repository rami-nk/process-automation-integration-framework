package io.miragon.libraryprocess;

import io.miragon.libraryprocess.adapter.in.worker.WorkerAutoConfiguration;
import io.miragon.libraryprocess.adapter.out.BookRequestConfirmationAutoConfiguration;
import io.miragon.libraryprocess.application.port.in.checkbookavailablity.CheckBookAvailabilityUseCase;
import io.miragon.libraryprocess.application.port.in.customermessage.CustomerMessageUseCase;
import io.miragon.libraryprocess.application.port.in.prepareshipment.PrepareShipmentUseCase;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationPort;
import io.miragon.libraryprocess.application.service.CheckBookAvailabilityService;
import io.miragon.libraryprocess.application.service.CustomerMessageService;
import io.miragon.libraryprocess.application.service.PrepareShipmentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        BookRequestConfirmationAutoConfiguration.class,
        WorkerAutoConfiguration.class,
})
public class LibraryProcessAutoConfiguration {

    @Bean
    public CheckBookAvailabilityUseCase checkBookAvailabilityUseCase() {
        return new CheckBookAvailabilityService();
    }

    @Bean
    public CustomerMessageUseCase customerMessageUseCase(final CustomerBookRequestConfirmationPort customerBookRequestConfirmationPort) {
        return new CustomerMessageService(customerBookRequestConfirmationPort);
    }

    @Bean
    public PrepareShipmentUseCase prepareShipmentUseCase() {
        return new PrepareShipmentService();
    }
}