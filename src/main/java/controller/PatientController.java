package controller;

import java.util.List;

import management.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
class PatientController {

    // Aggregate root
/*
    @GetMapping("/getPatients")
    public List<Patient> all() {
        return repository.findAll();
    }

    @PostMapping("/postPatients")
    public @ResponseBody Patient newPatient(Patient newPatient) {
        repository.save(newPatient);
        return newPatient;
    }

    @PostMapping("/findPatient")
    public @ResponseBody String findPatient(String name) {
        return name;
    }

    // Single item

    @GetMapping("/patients/{id}")
    Patient one(@PathVariable Long id) {

        return repository.findById(id);
    }

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable Long id) {
        repository.deleteById(id);
    }

     */
}

