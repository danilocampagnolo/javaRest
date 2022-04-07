package it.testspring.restapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    StudentController studentController;

    @Mock
    StudentService studentService;

    @BeforeEach
    void setUp() {
    }
}