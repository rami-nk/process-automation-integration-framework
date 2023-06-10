package io.miragon.libraryprocess.application.service;

import io.miragon.libraryprocess.application.port.in.customermessage.CustomerMessageInCommand;
import io.miragon.libraryprocess.application.port.in.customermessage.CustomerMessageUseCase;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationCommand;
import io.miragon.libraryprocess.application.port.out.CustomerBookRequestConfirmationPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerMessageService implements CustomerMessageUseCase {

    private final CustomerBookRequestConfirmationPort customerBookRequestConfirmationPort;

    @Override
    public void sendCustomerMessage(CustomerMessageInCommand customerMessageInCommand) {
        var customerBookRequestConfirmationCommand = new CustomerBookRequestConfirmationCommand();
        customerBookRequestConfirmationCommand.setFirstname(customerMessageInCommand.getFirstname());
        customerBookRequestConfirmationCommand.setLastname(customerMessageInCommand.getLastname());
        customerBookRequestConfirmationCommand.setAddress(customerMessageInCommand.getAddress());
        customerBookRequestConfirmationCommand.setCustomerId(customerMessageInCommand.getCustomerId());
        customerBookRequestConfirmationPort.confirmBookRequest(customerBookRequestConfirmationCommand);
    }
}