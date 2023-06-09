package io.miragon.libraryprocess.application.service;

import io.miragon.libraryprocess.application.port.in.customermessage.CustomerMessageInCommand;
import io.miragon.libraryprocess.application.port.in.customermessage.CustomerMessageUseCase;
import io.miragon.libraryprocess.application.port.out.CustomerMessageOutCommand;
import io.miragon.libraryprocess.application.port.out.CustomerMessagePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerMessageService implements CustomerMessageUseCase {

    private final CustomerMessagePort customerMessagePort;

    @Override
    public void sendCustomerMessage(CustomerMessageInCommand customerMessageInCommand) {
        var customerMessageOutCommand = new CustomerMessageOutCommand();
        customerMessageOutCommand.setFirstname(customerMessageInCommand.getFirstname());
        customerMessageOutCommand.setLastname(customerMessageInCommand.getLastname());
        customerMessageOutCommand.setAddress(customerMessageInCommand.getAddress());
        customerMessagePort.deliverCustomerMessage(customerMessageOutCommand);
    }
}