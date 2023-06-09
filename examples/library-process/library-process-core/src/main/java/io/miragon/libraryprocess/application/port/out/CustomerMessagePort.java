package io.miragon.libraryprocess.application.port.out;

public interface CustomerMessagePort {

    void deliverCustomerMessage(CustomerMessageOutCommand customerMessageOutCommand);
}
