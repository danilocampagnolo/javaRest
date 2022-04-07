package it.testspring.restapp.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    public void addNewSchool(School school) {
        Optional<School> schoolOptional = schoolRepository.findSchoolByName(school.getName());
        if (schoolOptional.isPresent()) {
            throw new IllegalStateException("Scuola già presente");
        }
        schoolRepository.save(school);
    }

    public void deleteSchool(Long id) {
        boolean exist = schoolRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Scuola non trovata");
        }

        schoolRepository.deleteById(id);
    }

    @Transactional
    public void updateSchool(Long id, School school) {
        School schoolFound = schoolRepository.findById(id).orElseThrow(() -> new IllegalStateException("La scuola non esiste"));

        if (school.getName() != null && school.getName().length() > 0 && !Objects.equals(schoolFound.getName(), school.getName())) {
            schoolFound.setName(school.getName());
        }

        if (school.getAddress() != null && school.getAddress().length() > 0 && !Objects.equals(schoolFound.getAddress(), school.getAddress())) {
            schoolFound.setAddress(school.getAddress());
        }

        schoolFound.setCapacity(school.getCapacity());

    }
}
