package management;

import exceptions.FormatException;
import exceptions.PersonAlreadyRegisteredException;

import java.util.Date;

public class Clerk extends Staff implements IRegistering, IChangeInformation {

    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        try {
            isPersonRegistered(person, department);
        } catch (PersonAlreadyRegisteredException e) {
            return false;
        }

        department.getPatients().add(person);
        addUniqueIdToPerson(person);

        return true;
    }

    public <T extends Person> void isPersonRegistered(T person, Department department) throws PersonAlreadyRegisteredException {
        // Search for same Unique ID
        for (Person patient : department.getStaff()) {
            if (patient.getUniqueId() == person.getUniqueId()) {
                throw new PersonAlreadyRegisteredException("Patient is already registered in the system");
            }
        }
    }
    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {
        // TODO: Can also be made with exceptions..

        return department.getPatients().contains(patient);
    }

    public void setPersonInformation(Person person, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        setPersonName(person, name);
        setPersonSurname(person, surname);
        setPersonBirthdate(person, birthdate);
        setPersonGender(person, gender);
        setPersonHomeAddress(person, homeAddress);
        setPersonPhoneNumber(person, phoneNumber);
    }

    public boolean setPersonPhoneNumber(Person person, int phoneNumber) {
        try {
            person.setPhoneNumber(phoneNumber);
            return true;
        } catch (FormatException e) {
            return false;
        }
    }

    public void setPersonHomeAddress(Person person, String homeAddress) {
        person.setHomeAddress(homeAddress);
    }

    public void setPersonGender(Person person, int gender) {
        person.setGender(gender);
    }

    public void setPersonBirthdate(Person person, Date birthdate) {
        person.setBirthdate(birthdate);
    }

    public void setPersonSurname(Person person, String surname) {
        person.setSurname(surname);
    }

    public void setPersonName(Person person, String name) {
        person.setName(name);
    }

}