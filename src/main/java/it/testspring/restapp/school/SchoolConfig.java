package it.testspring.restapp.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SchoolConfig {

    @Bean
    CommandLineRunner commandLineRunnerSchool(SchoolRepository repository) {
        return args -> {
            School enricoFermi = new School(
                    "E. Fermi",
                    "Via test, 12",
                    300
            );
            School cannizzaro = new School(
                    "Cannizzaro",
                    "Via test2, 122",
                    100
            );

            repository.saveAll(
                    List.of(enricoFermi, cannizzaro)
            );
        };
    }
}
