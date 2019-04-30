package management;

import exceptions.FormatException;
import exceptions.PersonNotFoundException;
import storage.Dao;
import storage.DaoPatientImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Clerk extends Staff implements IRegistering<Patient>, IChangeInformation, IQueryPatients<Patient> {

    private final Dao<Patient> dao = new DaoPatientImpl<>();

    //TODO This should be patient and not T.
    public boolean registerPerson(Patient person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        addUniqueIdToPerson(person);

        dao.save(person);
        department.getPatients().add(person);

        return true;
    }

    public boolean isPersonRegistered(Patient person, Department department) {
        // Search for same Unique ID
        // TODO: Revisit for efficiency (loop is always run completely. O(n))
        for (Patient patient : department.getPatients()) {
            if (patient.getUniqueId().equals(person.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Patient findpatient(Patient patient) throws PersonNotFoundException {
        Patient foundPatient = dao.find(patient);
        if (foundPatient != null) {
            return foundPatient;
        } else {
            throw new PersonNotFoundException("Patient not found in database");
        }
    }

    @Override
    public ArrayList<Patient> findpatient(HashMap<String, String> params) throws PersonNotFoundException {
        ArrayList<Patient> patients = dao.find(params);

        if (patients.isEmpty()) {
            throw new PersonNotFoundException("No patients were found with given parameters");
        } else {
            return patients;
        }
    }


    private void addUniqueIdToPerson(Person person) {
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
