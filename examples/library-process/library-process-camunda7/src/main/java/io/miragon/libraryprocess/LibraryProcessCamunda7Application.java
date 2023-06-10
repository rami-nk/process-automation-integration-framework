package io.miragon.libraryprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LibraryProcessAutoConfiguration.class)
public class LibraryProcessCamunda7Application {

    public static void main(String[] args) {
        SpringApplication.run(LibraryProcessCamunda7Application.class, args);
    }
}
