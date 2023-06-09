package io.miragon.libraryprocess.application.port.in.checkbookavailablity;

public interface CheckBookAvailabilityUseCase {

    CheckBookAvailabilityResult checkAvailability(CheckBookAvailabilityCommand checkBookAvailabilityCommand);
}