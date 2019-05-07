package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import core.buildings.Department;
import core.buildings.InDepartment;
import core.persons.*;

import exceptions.FormatException;
import org.springframework.security.access.prepost.PreAuthorize;
import persistence.data_access_objects.DaoDepartmentImpl;
import persistence.data_access_objects.DaoPatientImpl;
import persistence.query_roles.QueryRoleClerk;
import exceptions.PersonNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClerkController {
    QueryRoleClerk QRK = new QueryRoleClerk();
    PersonInformationFacade PIF;
    DaoDepartmentImpl<Department> daodept = new DaoDepartmentImpl<Department>();


    @PreAuthorize("hasRole('CLERK') or hasRole('ICT')")
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
                        ) throws Exception {

        Gender gender = Gender.valueOf((gen.toUpperCase()));


        Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        Patient newPatient = new Patient(name,surname, birthDate, gender, homeAddress, phoneNumber);

        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("name", department);

        try {
            Department d = daodept.find(hashMap).get(0);
            QRK.registerPerson(newPatient, d);
            d.add(newPatient);
        } catch(Exception e) {
            throw new Exception("Could not create patient, Department '"+department+"' could not be found");
        }

        return newPatient;

    }

    @GetMapping("/searchPatient")
    public @ResponseBody
    ArrayList<Person> findPatient(
            @RequestParam(value = "searchParameter", required=false) String choice,
            @RequestParam(value="text", required = true) String textbox2) throws Exception {

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
        try {
            return QRK.find(hashMap);

        } catch(Exception e) {

            throw new Exception ("Cannot find patient matching Parameter");
        }

    }
    @PreAuthorize("hasRole('CLERK') or hasRole('ICT')")
    @PostMapping(value = "/updatePatient")
    public @ResponseBody
    String updatePatient(@RequestParam(value="parameter") String param,
                         @RequestParam(value="id") String id,
                          @RequestParam(value="in-number", required = false) String number,
                         @RequestParam(value="in-text", required = false) String textbox,
                          @RequestParam(value="in-date", required = false) String date,
                          @RequestParam(value="in-gender", required = false) String gen) throws Exception {



        try {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("uniqueid",id);
            Patient patient = (Patient) QRK.find(hashMap).get(0);
            PIF = new PersonInformationFacade(patient);
        } catch(Exception e) {
            throw new Exception("Cant find patient with ID: " + id);
        }


        switch (param) {
            case "name":
                PIF.setPersonName(textbox);
                break;
            case "surname":
                PIF.setPersonSurname(textbox);
                break;
            case "birthdate":
                Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                PIF.setPersonBirthdate(birthDate);
                break;
            case "gender":
                Gender genderx = Gender.valueOf((gen.toUpperCase()));
                PIF.setPersonGender(genderx);
                break;
            case "homeAddress":
                PIF.setPersonHomeAddress(textbox);
                break;
            case "phoneNumber":
                PIF.setPersonPhoneNumber(Integer.valueOf(number));
                break;
        }

        return "Patient has been updated";
    }

    @PreAuthorize("hasRole('CLERK') or hasRole('ICT')")
    @PostMapping(value ="/deletePatient")
    public @ResponseBody
    String deletePerson(@RequestParam(value="id") String id) throws ParseException, PersonNotFoundException {


        HashMap<String, String> hashMapPatient = new HashMap<String, String>();
        hashMapPatient.put("uniqueId",id);

        Patient patient = (Patient) QRK.find(hashMapPatient).get(0);
        String departmentID = daodept.findDepartmentIdOfPerson(patient);

        HashMap<String, String> hashMapDepartment = new HashMap<String, String>();
        hashMapDepartment = new HashMap<String, String>();
        hashMapDepartment.put("uniqueId", departmentID);
        Department d = daodept.find(hashMapDepartment).get(0);

        QRK.delete(patient, d);

        return "patient deleted";
    }
}
