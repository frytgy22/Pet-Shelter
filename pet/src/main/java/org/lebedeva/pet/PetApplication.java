package org.lebedeva.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.processing.SupportedSourceVersion;

import static javax.lang.model.SourceVersion.RELEASE_6;

@SpringBootApplication
@SupportedSourceVersion(RELEASE_6)
public class PetApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetApplication.class, args);
    }
}
