package storage;

import exceptions.PersonNotFoundException;
import management.Department;
import management.InformationGenerator;
import management.Patient;
import management.Person;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleClerk implements IUpdate, IQuery {
    private final Dao<Patient> dao = new DaoPatientImpl<>();

    @Override
    public <T extends Person> T find(T obj) throws PersonNotFoundException {
        Patient foundPatient = dao.find((Patient) obj);
        if (foundPatient != null) {
            return (T) foundPatient;
        } else {
            throw new PersonNotFoundException("Patient not found in database");
        }

    }

    @Override
    public <T extends Person> ArrayList<T> find(HashMap<String, String> params, T table) throws PersonNotFoundException {
        ArrayList<Patient> patients = dao.find(params);

        if (patients.isEmpty()) {
            throw new PersonNotFoundException("No patients were found with given parameters");
        } else {
            return patients;
        }

    }

    @Override
    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        addUniqueIdToPerson(person);

        dao.save(person);
        department.getPatients().add(person);

        return true;
    }

    @Override
    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
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
    public <T> boolean delete(T obj, Department department) {
        if (dao.delete(patient)) {
            department.remove(patient);
            return true;
        } else {
            return false;
        }
    }

    private void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {

        return department.getPatients().contains(patient);
    }
}


//
//
//    //TODO This should be patient and not T.
//    public boolean registerPerson(Patient person, Department department) {
//        // Check that the person is not registered
//        if (isPersonRegistered(person, department)) {
//            return false;
//        }
//
//        addUniqueIdToPerson(person);
//
//        dao.save(person);
//        department.getPatients().add(person);
//
//        return true;
//    }
//
//    public boolean isPersonRegistered(Patient person, Department department) {
//        // Search for same Unique ID
//        // TODO: Revisit for efficiency (loop is always run completely. O(n))
//        for (Patient patient : department.getPatients()) {
//            if (patient.getUniqueId().equals(person.getUniqueId())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean delete(Patient patient, Department department) {
//        if (dao.delete(patient)) {
//            department.remove(patient);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public Patient findPatient(Patient patient) throws PersonNotFoundException {
//        Patient foundPatient = dao.find(patient);
//        if (foundPatient != null) {
//            return foundPatient;
//        } else {
//            throw new PersonNotFoundException("Patient not found in database");
//        }
//    }
//
//    @Override
//    public ArrayList<Patient> findPatient(HashMap<String, String> params) throws PersonNotFoundException {
//        ArrayList<Patient> patients = dao.find(params);
//
//        if (patients.isEmpty()) {
//            throw new PersonNotFoundException("No patients were found with given parameters");
//        } else {
//            return patients;
//        }
//    }
//
