package it.testspring.restapp.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunnerStudent(StudentRepository repository) {
        return args -> {
            Student miriam = new Student(
                    "Miriam",
                    LocalDate.of(2000, Month.DECEMBER,1),
                    "miriam@email.it"
            );
            Student alex = new Student(
                    "Alex",
                    LocalDate.of(2004, Month.DECEMBER,1),
                    "alex@email.it"
            );

            repository.saveAll(
                    List.of(miriam, alex)
            );
        };
    }
}
