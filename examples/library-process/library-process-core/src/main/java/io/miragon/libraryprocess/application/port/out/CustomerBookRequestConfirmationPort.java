package io.miragon.libraryprocess.application.port.out;

public interface CustomerBookRequestConfirmationPort {

    void confirmBookRequest(CustomerBookRequestConfirmationCommand customerBookRequestConfirmationCommand);
}