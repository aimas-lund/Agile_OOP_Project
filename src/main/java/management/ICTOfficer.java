package management;

import exceptions.*;
import storage.Dao;
import storage.DaoStaffImpl;
import exceptions.PersonNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ICTOfficer extends Staff implements IRegistering, IChangeInformation, IQuery {
    // TODO: Replace duplicate code for ICT Officer and Clerk, or find different approach

    private final Dao<Staff> daostaff= new DaoStaffImpl<>();

    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        Dao<Staff> daostaff = new DaoStaffImpl<>();

        // Give unique id
        addUniqueIdToPerson(person);

        // Give person email
        String email = InformationGenerator.generateEmail((Staff) person);
        ((Staff) person).setEmail(email);

        // Add person to database
        daostaff.save((Staff) person);
        department.getStaff().add((Staff) person);
        return true;
    }

    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
        // Search for same Unique ID
        for (Person staff : department.getStaff()) {
            // TODO: Optimize find functionality, now is O(n)
            if (staff.getUniqueId() == person.getUniqueId()) {
                return true;
            }
        }
        return false;
    }

    public void setDoctorInformation(Doctor doctor, Speciality speciality, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        doctor.setSpeciality(speciality);

        setPersonInformation(doctor, name, surname, birthdate, gender, homeAddress, phoneNumber);
    }

    private void addUniqueIdToPerson(Person person) {
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


    public Object find(Object obj) throws PersonNotFoundException {
        // TODO: Will be added later
        return null;
    }


    @Override
    public ArrayList<Staff> find(HashMap params) throws PersonNotFoundException {
        ArrayList staffs = daostaff.find(params);

        if (staffs.isEmpty()) {
            throw new PersonNotFoundException("Person was not found with given parameters");
        } else {
            return staffs;
        }
    }
}
