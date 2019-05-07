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
    PersonInformationFacade PIF;
    InDepartment mockDept = new InDepartment();


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



        QRK.registerPerson(newPatient, mockDept);

        return newPatient;

    }

    @GetMapping("/searchPatient")
    public @ResponseBody
    ArrayList<Person> findPatient(
            @RequestParam(value = "searchParameter", required=false) String choice,
            @RequestParam(value="text", required = true) String textbox2) throws PersonNotFoundException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        if(choice.equals("name")) {
            hashMap.put("name", textbox2);
        }
        else if(choice.equals("id")) {
            hashMap.put("uniqueid",textbox2);
        }
        else if(choice.equals("department")) {
            hashMap.put("department", textbox2);
        }
        //return id;
        return QRK.find(hashMap);

    }

    @PostMapping(value = "/updatePatient")
    public @ResponseBody
    String updatePatient(@RequestParam(value="id") String id,
                          @RequestParam(value="name", required = false) String name,
                          @RequestParam(value="surname", required = false) String surname,
                          @RequestParam(value="birthday", required = false) String birthdate,
                          @RequestParam(value = "gender", required = false) String gender,
                          @RequestParam(value = "homeAddress", required = false) String homeAddress,
                          @RequestParam(value="phoneNumber", required = false) String phoneNumber,
                          @RequestParam(value="text", required = false) String textbox,
                          @RequestParam(value="number", required = false) String number,
                          @RequestParam(value="date", required = false) String date,
                          @RequestParam(value="gen", required = false) String gen) throws ParseException, PersonNotFoundException {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uniqueid",id);

        Patient patient = (Patient) QRK.find(hashMap).get(0);
        PIF = new PersonInformationFacade(patient);

        if (name!= null) {
            PIF.setPersonName(textbox);
        }
        else if (surname != null)
            PIF.setPersonSurname(textbox);
        else if (birthdate != null) {
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            PIF.setPersonBirthdate(birthDate);
        }
        else if (gender!=null) {
            Gender genderx = Gender.valueOf((gen.toUpperCase()));
            PIF.setPersonGender(genderx);
        }
        else if (homeAddress != null)
            PIF.setPersonHomeAddress(textbox);
        else if (phoneNumber!= null)
            PIF.setPersonPhoneNumber(Integer.valueOf(number));

        return "Patient has been updated";
    }

    @PostMapping(value ="/deletePatient")
    public @ResponseBody
    String deletePerson(@RequestParam(value="id") String id) throws ParseException, PersonNotFoundException {


        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("uniqueid",id);

        Patient patient = (Patient) QRK.find(hashMap).get(0);


        QRK.delete(patient, mockDept);

        return "patient deleted";

    }

}
