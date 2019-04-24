package storage;

import org.springframework.security.access.prepost.PreAuthorize;

public class ChangeInfo {

    @PreAuthorize("hasRole('doctor') or hasRole('ict')")
    public void setHealthData(){
        //TODO: set patient health data.
        // health data doesnt exist yet, thus no methods to use yet

    }
    @PreAuthorize("hasRole('clerk') or hasRole('ict')")
    public void RegisterPatientData(){
        //TODO: Register new patient.
        //Use Interface IRegister with method
            // registerPerson(Person, Department)
            // addUniqueIdToPerson (person)
    }
    @PreAuthorize("hasRole('clerk') or hasRole('ict')")
    public void ChangePatientData(){
        //TODO: guess what, changes patient data.
        //Use interface IchangeInformation with methods
            //setPersonInformation(Person person, String name, String surname, Date birthdate, int gender, String homeAddress, int phoneNumber)
            //setPersonPhoneNumber(person, phoneNumber)
            //setPersonHomeAddress(person, HomeAddress)
            //setPersonGender(Person, Gender)
            //setPersonBirthdate(Person, birthdate)
            //setPersonSurname(Person, surname)
            //setPersonName(person, name)
    }
    @PreAuthorize("hasRole('clerk') or hasRole('ict')")
    public void setAdmissionData(){
        //TODO or not TODO: assign a person to a department
        //Use an interface or method that will allow to assign to a department
        //Maybe use Department class :
        // Department.assign(Patient)
        // TODO: create a in/out method assignment for a patient p.

    }
    @PreAuthorize("hasRole('ict')")
    public void RegisterStaff(){
        //TODO: Register new staff.
        ////Use class methods from ICT (implements IRegistering and IChangeInformation):
        //            //setPersonInformation(Person person, String name, String surname, Date birthdate, int gender, String homeAddress, int phoneNumber)
        //            //setPersonPhoneNumber(person, phoneNumber)
        //            //setPersonHomeAddress(person, HomeAddress)
        //            //setPersonGender(Person, Gender)
        //            //setPersonBirthdate(Person, birthdate)
        //            //setPersonSurname(Person, surname)
        //            //setPersonName(person, name)
        //            //TODO: setDoctorDepartment(Doctor d, Department dep)
                      //TODO: setSpeciality(Staff f)
    }
    @PreAuthorize("hasRole('ict')")
    public void ChangeStaff(){
        //TODO: Change staff information
        //Use class methods from ICT (implements IRegistering and IChangeInformation):
        //setPersonInformation(Person person, String name, String surname, Date birthdate, int gender, String homeAddress, int phoneNumber)
        //setPersonPhoneNumber(person, phoneNumber)
        //setPersonHomeAddress(person, HomeAddress)
        //setPersonGender(Person, Gender)
        //setPersonBirthdate(Person, birthdate)
        //setPersonSurname(Person, surname)
        //setPersonName(person, name)
        //TODO: setSpeciality(Staff f)
    }
}

