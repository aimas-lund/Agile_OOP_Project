package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import core.buildings.Department;
import core.persons.*;

import core.utility.Speciality;
import exceptions.PersonNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.query_roles.QueryRoleICT;

import javax.print.Doc;

@RestController
class ICTController {

    Doctor doctor;
    Nurse nurse;
    ICTOfficer ict;
    Clerk clerk;
    QueryRoleICT QRICT = new QueryRoleICT();
    DaoDepartmentImpl<Department> daodept = new DaoDepartmentImpl<Department>();


    // Search Patient inherited from Clerk

/*

    @GetMapping("/getPatients")
    public List<Patient> all() {
        return repository.findAll();
    }

*/

    @PostMapping(value ="/registerStaff")
    public @ResponseBody
    Staff newStaff(@RequestParam(value="name") String name,
                   @RequestParam(value="surname") String surname,
                   @RequestParam(value="birthday") String birthdate,
                   @RequestParam(value = "gender") String gen,
                   @RequestParam(value = "homeAddress") String homeAddress,
                   @RequestParam(value="phoneNumber") int phoneNumber,
                   @RequestParam(value="role") String role,
                   @RequestParam(value="speciality", required = false) String speciality,
                   @RequestParam(value="department") String department) throws ParseException {

        Gender gender = Gender.valueOf((gen.toUpperCase()));

        Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        Speciality spec = Speciality.valueOf(speciality.toUpperCase());
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name", department);
        Department d = daodept.find(hashMap).get(0);

        if (role.equals("Doctor")) {
            doctor = new Doctor(spec,name,surname,birthDate,gender,homeAddress,phoneNumber);
            QRICT.registerPerson(doctor, d);
            d.add(doctor);
            return doctor;
        }
        else if (role.equals("Nurse")) {
            nurse = new Nurse(name,surname,birthDate,gender,homeAddress,phoneNumber);
            QRICT.registerPerson(nurse, d);
            d.add(nurse);
            return nurse;
        }
        else if (role.equals("Clerk")) {
            clerk = new Clerk(name,surname,birthDate,gender,homeAddress,phoneNumber);
            QRICT.registerPerson(clerk, d);
            d.add(clerk);
            return clerk;
        }
        else {
            ict = new ICTOfficer(name,surname,birthDate,gender,homeAddress,phoneNumber);
            QRICT.registerPerson(ict, d);
            d.add(ict);
            return ict;
        }

    }


    @GetMapping("/searchStaff")
    public @ResponseBody
    ArrayList<Staff> findStaff(
            @RequestParam(value = "searchParameter", required=false) String choice,
            @RequestParam(value="text", required = true) String textbox) throws PersonNotFoundException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        if(choice.equals("name")) {
            hashMap.put("name", textbox);
        }
        else if(choice.equals("id")) {
            hashMap.put("uniqueid",textbox);
        }
        else if(choice.equals("department")) {
            hashMap.put("speciality", textbox);
        }
        //return id;
        return QRICT.findStaff(hashMap);

    }
//    @PostMapping(value = "/registerPatient")
//    public @ResponseBody
//        //Patient newPatient(Patient newPatient) {
//    Patient newPatient(@RequestParam(value="name") String name,
//                       @RequestParam(value="surname") String surname,
//                       @RequestParam(value="birthday") String birthdate,
//                       @RequestParam(value = "gender") String gen,
//                       @RequestParam(value = "homeAddress") String homeAddress,
//                       @RequestParam(value="phoneNumber") int phoneNumber) throws ParseException {
//
//
//        Gender gender = Gender.valueOf((gen.toUpperCase()));
//
//
//        Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
//        Patient newPatient = new Patient(name, surname, birthDate, gender, homeAddress, phoneNumber);
//        return newPatient;
//
//    }





//(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber)


//    // Single item
//
//    @GetMapping("/patients/{id}")
//    Patient one(@PathVariable Long id) {
//
//        return repository.findById(id);
//    }
//
//    @DeleteMapping("/patients/{id}")
//    void deletePatient(@PathVariable Long id) {
//        repository.deleteById(id);
//    }

}

