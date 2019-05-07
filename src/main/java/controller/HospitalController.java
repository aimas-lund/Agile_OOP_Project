package controller;


import core.buildings.*;
import core.persons.Hospital;
import core.persons.Patient;
import core.persons.Person;
import core.persons.Staff;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.query_roles.QueryRoleICT;

import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class HospitalController {
    private LoadHospital loader = new LoadHospital();
    private Hospital hospital;
    private DaoDepartmentImpl<Department> DAO = new DaoDepartmentImpl<>();
    private QueryRoleICT QRI = new QueryRoleICT();

    public Hospital getHospital() {
        this.hospital = loader.getHospital();
        return this.hospital;
    }

    @PostMapping("/addDepartment")
    public Department addDepartment( @RequestParam(value="id") String id,
                               @RequestParam(value="name") String name,
                               @RequestParam(value="type") String type,
                               @RequestParam(value="capacity",required=false) String capacity
                                ) throws Exception {
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
            throw new Exception("Department unable to register");
        }
        Hospital hospital = getHospital();
        hospital.add(dept);

        return dept;
    }
    @PostMapping("/removeDepartment")
    public String removeDepartment( @RequestParam(value="id") String id) {
        Hospital hospital = getHospital();
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("uniqueid",id);
        ArrayList<Department> dept = DAO.find(hashMap);
        if (dept.size() != 0) {
            hospital.remove(dept.get(0));
            return "Deleted department with id: " + id;
        }
        return "Cant find department with id: " + id;
    }

    @PostMapping("/movePerson")
    public String movePerson( @RequestParam(value="personId") String personId,
                              @RequestParam(value="toId") String toId,
                              @RequestParam(value="type") String type
                            ) throws Exception {

        HashMap<String,String> personMap = new HashMap<>();
        HashMap<String,String> deptMap = new HashMap<>();

        Person person = null;

        deptMap.put("uniqueid",toId);
        ArrayList<Department> d2 = DAO.find(deptMap);
        if (d2.size() == 0) {
            throw new Exception("Cant find to department with ID: " + d2);
        }
        personMap.put("uniqueid",personId);

        Hospital hospital = getHospital();
        String deptId;
        ArrayList<Department> d1 = null;
        if (type.equals("patient")) {
            ArrayList<Patient> patient = QRI.findPatient(personMap);
            if (patient.size() == 0) {
                throw new Exception("Cant find patient with ID: " + personId);
            }
            person = patient.get(0);
            deptId = DAO.findDepartmentIdOfPerson(((Patient) person));
            deptMap.put("uniqueid",deptId);
            d1 = DAO.find(deptMap);
            if (d1.size() == 0) {
                throw new Exception("Cant find from department with ID: " + d2);
            }
            hospital.move((Patient) person,d1.get(0),d2.get(0));

        } else if (type.equals("staff")) {
            ArrayList<Staff> staff = QRI.findStaff(personMap);
            if (staff.size() == 0) {
                throw new Exception("Cant find staff with ID: " + personId);
            }
            person = staff.get(0);
            deptId = DAO.findDepartmentIdOfPerson(((Staff) person));
            deptMap.put("uniqueid",deptId);
            d1 = DAO.find(deptMap);
            if (d1.size() == 0) {
                throw new Exception("Cant find from department with ID: " + d2);
            }
            hospital.move((Staff) person,d1.get(0),d2.get(0));

        }

        return "Moved " + person.getName() + " from " + d1.get(0).getName() + " to " + d2.get(0).getName();
    }

    @PostMapping("/assignPerson")
    public String assignPerson(@RequestParam(value="personId") String personId,
                               @RequestParam(value="toId") String deptId,
                               @RequestParam(value="type") String type) throws Exception {
        HashMap<String,String> personMap = new HashMap<>();
        HashMap<String,String> deptMap = new HashMap<>();

        deptMap.put("uniqueid",deptId);
        ArrayList<Department> depts = DAO.find(deptMap);
        if (depts.isEmpty()) {
            throw new Exception("Cant find department with ID: " + deptId);
        }
        Department dept = depts.get(0);

        personMap.put("uniqueid",personId);
        if (type.equals("patient")) {
            Patient patient = QRI.findPatient(personMap).get(0);
            dept.add(patient);
        } else if (type.equals("staff")) {
            Staff staff = QRI.findStaff(personMap).get(0);
            dept.add(staff);
        }
        return "Successfully added " + type + " with ID (" + personId + ") to department " + dept.getName();

    }

}
