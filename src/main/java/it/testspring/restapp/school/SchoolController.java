package it.testspring.restapp.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public List<School> getSchools() {
        return schoolService.getSchools();
    }

    @PostMapping
    public void addNewSchool(@RequestBody School school) {
        schoolService.addNewSchool(school);
    }

    @DeleteMapping(path = "{id}")
    public void deleteSchool(@PathVariable("id") Long id) {
        schoolService.deleteSchool(id);
    }

    @PutMapping(path = "{id}")
    public void updateSchool(@PathVariable("id") Long id, @RequestBody School school) {
        schoolService.updateSchool(id, school);
    }
}
