package it.testspring.restapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll(); //ritorna una lista di tutti gli studenti
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("email già presente");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exist = studentRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Lo studente con id" + id + "non esiste.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                   String.format("Lo studente con id %s non esiste.", id)
                ));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email già presente");
            }
            student.setEmail(email);
        }
    }

    @Transactional
    public void updateStudentFromBody(Long id, Student student) {
        Student studentFound = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                   String.format("Lo studente con id %s non esiste.", id)
                ));

        if (student.getName() != null && student.getName().length() > 0 && !Objects.equals(studentFound.getName(), student.getName())) {
            studentFound.setName(student.getName());
        }

        if (student.getEmail() != null && student.getEmail().length() > 0 && !Objects.equals(studentFound.getEmail(), student.getEmail())) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email già presente");
            }
            studentFound.setEmail(student.getEmail());
        }
    }
}
