package controller;

import core.persons.Patient;
import org.springframework.web.bind.annotation.RestController;
import persistence.data_access_objects.DaoPatientImpl;

@RestController
public class NurseController {

    DaoPatientImpl<Patient> daopatient = new DaoPatientImpl<Patient>();

    // Search Patient maps to Doctor Controller


}
