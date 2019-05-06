package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import core.persons.*;

import core.utility.Speciality;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;

@RestController
class ICTController {

    // Aggregate root




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
                   @RequestParam(value="speciality",required = false) String speciality) throws ParseException {

        Gender gender = Gender.valueOf((gen.toUpperCase()));

        Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);



        Doctor doctor = new Doctor();


        if (role.equals("Doctor")) {
            Speciality spec = Speciality.valueOf(speciality.toUpperCase());
            doctor = new Doctor(spec,name,surname,birthDate,gender,homeAddress,phoneNumber);

        }


        return doctor;
    }

    /*
    @PostMapping(value = "/registerPatient")
    public @ResponseBody
        //Patient newPatient(Patient newPatient) {
    Patient newPatient(@RequestParam(value="name") String name,
                       @RequestParam(value="surname") String surname,
                       @RequestParam(value="birthday") String birthdate,
                       @RequestParam(value = "gender") String gen,
                       @RequestParam(value = "homeAddress") String homeAddress,
                       @RequestParam(value="phoneNumber") int phoneNumber) throws ParseException {


        Gender gender = Gender.valueOf((gen.toUpperCase()));


        Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(birthdate);
        Patient newPatient = new Patient(name,surname, birthDate, gender, homeAddress, phoneNumber);
        return newPatient;

    }





//(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber)
/*

    @PostMapping("/findPatient")
    public @ResponseBody String findPatient(String name) {
        return name;
    }

    // Single item

    @GetMapping("/patients/{id}")
    Patient one(@PathVariable Long id) {

        return repository.findById(id);
    }

    @DeleteMapping("/patients/{id}")
    void deletePatient(@PathVariable Long id) {
        repository.deleteById(id);
    }

     */
}

