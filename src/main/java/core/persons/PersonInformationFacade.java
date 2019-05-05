package core.persons;

import core.utility.Speciality;
import exceptions.FormatException;

import java.util.Date;

public class PersonInformationFacade {
    private final Person person;

    public PersonInformationFacade(Person person) {
        this.person = person;
    }

    public void setPersonInformation(String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        setPersonName(name);
        setPersonSurname(surname);
        setPersonBirthdate(birthdate);
        setPersonGender(gender);
        setPersonHomeAddress(homeAddress);
        setPersonPhoneNumber(phoneNumber);
    }

    public boolean setPersonPhoneNumber(int phoneNumber) {
        try {
            person.setPhoneNumber(phoneNumber);
            return true;
        } catch (FormatException e) {
            return false;
        }
    }

    public void setPersonHomeAddress(String homeAddress) {
        person.setHomeAddress(homeAddress);
    }

    public void setPersonGender(int gender) {
        person.setGender(gender);
    }

    public void setPersonBirthdate(Date birthdate) {
        person.setBirthdate(birthdate);
    }

    public void setPersonSurname(String surname) {
        person.setSurname(surname);
    }

    public void setPersonName(String name) {
        person.setName(name);
    }

    public void setPersonUniqueId(String uniqueId) {
        person.setUniqueId(uniqueId);
    }

    public boolean setStaffEmail(String email) {
        if (person instanceof Staff) {
            return ((Staff) person).setEmail(email);
        }
        return false;
    }

    public void setStaffInitials(String initials) {
        if (person instanceof Staff) {
            ((Staff) person).setInitials(initials);
        }
    }

    public void setPatientAlive(boolean alive) {
        if (person instanceof Patient) {
            ((Patient) person).setAlive(alive);
        }
    }

    public void setDoctorSpeciality(Speciality speciality) {
        if (person instanceof Doctor) {
            ((Doctor) person).setSpeciality(speciality);
        }
    }
}
