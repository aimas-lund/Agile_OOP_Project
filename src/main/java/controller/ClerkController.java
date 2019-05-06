package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import core.buildings.Department;
import core.buildings.InDepartment;
import core.persons.*;

import exceptions.FormatException;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.query_roles.QueryRoleClerk;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClerkController {
    QueryRoleClerk QRK = new QueryRoleClerk();
    DaoDepartmentImpl dept = new DaoDepartmentImpl();
    PersonInformationFacade PIF;

    @PostMapping(value = "/registerPatient")
    public @ResponseBody
        //Patient newPatient(Patient newPatient) {
    Patient newPatient(@RequestParam(value="name") String name,
                       @RequestParam(value="surname") String surname,
                       @RequestParam(value="birthday") String birthdate,
                       @RequestParam(value = "gender") String gen,
                       @RequestParam(value = "homeAddress") String homeAddress,
                       @RequestParam(value="phoneNumber") int phoneNumber,
                        @RequestParam(value="department") String department
                        ) throws ParseException {

        Gender gender = Gender.valueOf((gen.toUpperCase()));

        Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        Patient newPatient = new Patient(name,surname, birthDate, gender, homeAddress, phoneNumber);

        InDepartment mockDept = new InDepartment();


        QRK.registerPerson(newPatient, mockDept);

        return newPatient;

    }

    @PostMapping("/findPatient")
    public @ResponseBody
    ArrayList<Person> findPatient(
            @RequestParam(value = "name", required=false) String name,
            @RequestParam(value="id", required =false) String id,
            @RequestParam(value = "department", required = false) String department) throws PersonNotFoundException {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        if(name!=null) {
            hashMap.put("name", name);
        }
        else if(id!=null) {
            hashMap.put("uniqueid",id);
        }
        else if(department!= null) {
            hashMap.put("department", department);
        }
        return QRK.find(hashMap);

    }

    @PostMapping(value = "/updatePatient")
    public @ResponseBody
    Patient updatePatient(@RequestParam(value="id") String id,
                          @RequestParam(value="name", required = false) String name,
                          @RequestParam(value="surname", required = false) String surname,
                          @RequestParam(value="birthday", required = false) String birthdate,
                          @RequestParam(value = "gender", required = false) String gen,
                          @RequestParam(value = "homeAddress", required = false) String homeAddress,
                          @RequestParam(value="phoneNumber", required = false) String phoneNumber) throws ParseException, PersonNotFoundException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id",id);

        Patient patient = (Patient) QRK.find(hashMap).get(0);
        PIF = new PersonInformationFacade(patient);

        if (name!= null) {
            PIF.setPersonName(name);
        }
        else if (surname != null)
            PIF.setPersonSurname(name);
        else if (birthdate != null) {
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
            PIF.setPersonBirthdate(birthDate);
        }
        else if (gen!=null) {
            Gender gender = Gender.valueOf((gen.toUpperCase()));
            PIF.setPersonGender(gender);
        }
        else if (homeAddress != null)
            PIF.setPersonHomeAddress(homeAddress);
        else if (phoneNumber!=null)
            PIF.setPersonPhoneNumber(Integer.valueOf(phoneNumber));

        return patient;
    }

}
