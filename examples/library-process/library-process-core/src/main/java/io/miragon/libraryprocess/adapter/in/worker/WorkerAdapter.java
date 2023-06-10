package io.miragon.libraryprocess.adapter.in.worker;

import io.miragon.libraryprocess.application.port.in.checkbookavailablity.CheckBookAvailabilityCommand;
import io.miragon.libraryprocess.application.port.in.checkbookavailablity.CheckBookAvailabilityResult;
import io.miragon.libraryprocess.application.port.in.checkbookavailablity.CheckBookAvailabilityUseCase;
import io.miragon.libraryprocess.application.port.in.prepareshipment.PrepareShipmentCommand;
import io.miragon.libraryprocess.application.port.in.prepareshipment.PrepareShipmentUseCase;
import io.miragon.servicetaskapi.api.Worker;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkerAdapter {

    private final CheckBookAvailabilityUseCase checkBookAvailabilityUseCase;
    private final PrepareShipmentUseCase prepareShipmentUseCase;

    @Worker
    public CheckBookAvailabilityResult checkBookAvailability(CheckBookAvailabilityCommand checkBookAvailabilityCommand) {
        return checkBookAvailabilityUseCase.checkAvailability(checkBookAvailabilityCommand);
    }

    @Worker
    public void prepareShipment(PrepareShipmentCommand prepareShipmentCommand) {
        prepareShipmentUseCase.prepareShipment(prepareShipmentCommand);
    }
}