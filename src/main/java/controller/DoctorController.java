package controller;

import core.buildings.Department;
import core.buildings.OutDepartment;
import core.persons.Doctor;
import core.persons.Patient;
import core.persons.Person;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.*;
import persistence.data_access_objects.DaoDepartmentImpl;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class DoctorController {

    DaoDepartmentImpl<Department> dao = new DaoDepartmentImpl<Department>();

    Doctor doctor = new Doctor();
    // search patients

//    @PostMapping("/findPatient")
//    public @ResponseBody
//    ArrayList<Person> findPatient(
//            @RequestParam(value = "name", required=false) String name,
//            @RequestParam(value="id", required =false) String id,
//            @RequestParam(value = "department", required = false) String department) throws PersonNotFoundException {
//
//
//        HashMap<String, String> hashMap = new HashMap<String, String>();
//        hashMap.put("name",name);
//        hashMap.put("id",id);
//        hashMap.put("department",department);
//
//
//
//        return QRK.find(hashMap);
//
//    }

    // get waiting patients

    @GetMapping("/nextPatient")
    public @ResponseBody
    Patient getWaitingPatient(@RequestParam(value = "department") String department){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("department",department);
        ArrayList<Department> depts = dao.find(hashMap);
        OutDepartment dept = (OutDepartment) depts.get(0);
        return dept.getNextWaitingPatient();
    }
}
