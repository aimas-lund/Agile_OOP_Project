package management;

import exceptions.FormatException;
import exceptions.PersonAlreadyRegisteredException;

import java.util.Date;

public class Clerk extends Staff implements IRegistering, IChangeInformation {

    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        isPersonRegistered(person, department);

        department.getPatients().add((Patient) person);
        addUniqueIdToPerson(person);

        return true;
    }

    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
        // Search for same Unique ID
        // TODO: Revisit for efficiency (loop is always run completely. O(n))
        for (Patient patient : department.getPatients()) {
            if (patient.getUniqueId() == person.getUniqueId()) {
                return true;
            }
        }
        return false;
    }

    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {

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
