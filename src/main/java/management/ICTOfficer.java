package management;

import exceptions.FormatException;
import exceptions.PersonAlreadyRegisteredException;

import java.util.Date;

public class ICTOfficer extends Staff implements IRegistering, IChangeInformation {
    // TODO: Replace duplicate code for ICT Officer and Clerk, or find different approach

    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        try {
            isPersonRegistered(person, department);
        } catch (PersonAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
            return false;
        }

        // Add person to database
        department.getStaff().add(person);
        // Give unique id
        addUniqueIdToPerson(person);
        return true;
    }

    public <T extends Person> void isPersonRegistered(T person, Department department) throws PersonAlreadyRegisteredException {
        // Search for same Unique ID
        for (Person staff : department.getStaff()) {
            // TODO: Optimize find functionality, now is O(n)
            if (staff.getUniqueId() == person.getUniqueId()) {
                throw new PersonAlreadyRegisteredException("Staff member is already registered in the system");
            }
        }
    }

    public void setDoctorInformation(Doctor doctor, Speciality speciality, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        doctor.setSpeciality(speciality);

        setPersonInformation(doctor, name, surname, birthdate, gender, homeAddress, phoneNumber);
    }

    public void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    @SuppressWarnings("Duplicates")
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
