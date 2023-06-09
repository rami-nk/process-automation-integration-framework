package io.miragon.libraryprocess.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBookRequestConfirmationCommand {

    private String firstname;

    private String lastname;

    private String address;

    private String customerId;
}