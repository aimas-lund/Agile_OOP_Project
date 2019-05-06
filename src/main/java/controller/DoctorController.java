package controller;

import core.buildings.Department;
import core.buildings.OutDepartment;
import core.persons.Doctor;
import core.persons.Patient;
import core.persons.Person;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.*;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.data_access_objects.DaoPatientImpl;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class DoctorController {

    DaoDepartmentImpl<Department> daodept = new DaoDepartmentImpl<Department>();
    DaoPatientImpl<Patient> daopatient = new DaoPatientImpl<Patient>();

    Doctor doctor = new Doctor();
    // search patients

    // get waiting patients

    @GetMapping("/nextPatient")
    public @ResponseBody
    Patient getWaitingPatient(@RequestParam(value = "department") String department){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("department",department);
        ArrayList<Department> depts = daodept.find(hashMap);
        OutDepartment dept = (OutDepartment) depts.get(0);
        return dept.getNextWaitingPatient();
    }
}
