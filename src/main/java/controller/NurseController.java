package controller;

import core.buildings.Department;
import core.buildings.ERDepartment;
import core.buildings.InDepartment;
import core.buildings.OutDepartment;
import core.persons.Patient;
import org.springframework.web.bind.annotation.*;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.data_access_objects.DaoPatientImpl;
import persistence.query_roles.QueryRoleNurse;

import java.util.ArrayList;
import java.util.HashMap;

import static com.sun.tools.doclint.Entity.or;

@RestController
public class NurseController {

    DaoPatientImpl<Patient> daopatient = new DaoPatientImpl<Patient>();
    DaoDepartmentImpl<Department> daoDepartment = new DaoDepartmentImpl<Department>();
    QueryRoleNurse QRN = new QueryRoleNurse();

    // TODO: 2019-05-07 what about ER departments tho 
    // Search Patient maps to Doctor Controller

    @PostMapping("/assignPatient")
    public @ResponseBody
    Patient getWaitingPatient(@RequestParam(value = "id") String id, @RequestParam(value = "department") String department) {

        // find department
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("department", department);
        Department dept = (Department) daoDepartment.find(hashMap).get(0);

        // find patient
        hashMap = new HashMap<String, String>();
        hashMap.put("uniqueid", id);

        Patient patient = (Patient) daopatient.find(hashMap).get(0);

        if (dept instanceof InDepartment) {
            QRN.assignPatientToBed(patient, (InDepartment) dept);
        } else if (dept instanceof OutDepartment) {
            QRN.assignPatientToWaitingRoom(patient, (OutDepartment) dept);
        } 

        return patient;
    }

}