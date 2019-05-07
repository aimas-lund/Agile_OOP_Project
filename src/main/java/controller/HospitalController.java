package controller;


import core.buildings.*;
import core.persons.Doctor;
import core.persons.Patient;
import core.persons.Person;
import core.persons.Staff;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.query_roles.QueryRoleICT;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class HospitalController {
    LoadHospital loader = new LoadHospital();
    private Hospital hospital;
    DaoDepartmentImpl<Department> DAO = new DaoDepartmentImpl<>();
    QueryRoleICT QRI = new QueryRoleICT();

    public Hospital getHospital() {
        this.hospital = loader.getHospital();
        return this.hospital;
    }

    @PostMapping("/addDepartment")
    public Department addDepartment( @RequestParam(value="id") String id,
                               @RequestParam(value="name") String name,
                               @RequestParam(value="type") String type,
                               @RequestParam(value="capacity",required=false) String capacity
                                ) {
        Department dept;
        if ("out".equals(type)) {
            dept = new OutDepartment(id, name);
        } else if ("in".equals(type)) {
            int cap = Integer.valueOf(capacity);
            dept = new InDepartment(id, name, cap);
        } else if ("er".equals(type)) {
            int cap = Integer.valueOf(capacity);
            dept = new ERDepartment(id, name, cap);
        } else {
            dept = new OutDepartment("id","test");
        }
        Hospital hospital = getHospital();
        hospital.add(dept);

        //DAO.save(dept);

        return dept;
    }
    @PostMapping("/removeDepartment")
    public String removeDepartment( @RequestParam(value="id") String id) {
        Hospital hospital = getHospital();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("uniqueid",id);
        Department dept = DAO.find(hashMap).get(0);
        hospital.remove(dept);

        //DAO.delete(id);

        return "Deleted department with id: " + id;
    }

    @PostMapping("/movePerson")
    public String movePerson( @RequestParam(value="personId") String personId,
                              @RequestParam(value="toId") String toId,
                              @RequestParam(value="type") String type
                            ) throws PersonNotFoundException {

        HashMap<String,String> personMap = new HashMap<>();
        HashMap<String,String> deptMap = new HashMap<>();

        deptMap.put("uniqueid",toId);
        Department d2 = DAO.find(deptMap).get(0);
        personMap.put("uniqueid",personId);

        Hospital hospital = getHospital();
        Person person = null;
        String deptId;
        Department d1 = null;
        if (type.equals("patient")) {
            Patient patient = QRI.findPatient(personMap).get(0);
            person = patient;
            deptId = DAO.findDepartmentIdOfPerson(patient);
            deptMap.put("uniqueid",deptId);
            d1 = DAO.find(deptMap).get(0);
            hospital.move(patient,d1,d2);
        } else if (type.equals("staff")) {
            Staff staff = QRI.findStaff(personMap).get(0);
            person = staff;
            deptId = DAO.findDepartmentIdOfPerson(staff);
            deptMap.put("uniqueid",deptId);
            d1 = DAO.find(deptMap).get(0);
            hospital.move(staff,d1,d2);
        }

        return "Moved " + person.getName() + " from " + d1.getName() + " to " + d2.getName();
    }

}
