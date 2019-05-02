package storage;

import exceptions.PersonNotFoundException;
import management.*;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleClerk implements IUpdate, IQuery {
    private final IDao<Patient> daoPatient = new DaoPatientImpl<>();

    @Override
    public <T extends Person> boolean registerPerson(T person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        if (person.getUniqueId() == null) {
            String uniqueId = InformationGenerator.generateUniqueID(person);
            new PersonInformationFacade(person).setPersonUniqueId(uniqueId);
        }

        daoPatient.save((Patient) person);
        department.add((Patient) person);

        return true;
    }

    @Override
    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
        // Search for same Unique ID
        // TODO: Revisit for efficiency (loop is always run completely. O(n))
        for (Patient patient : department.getPatients()) {
            if (patient.getUniqueId().equals(person.getUniqueId())) return true;
        }
        return false;
    }

    public <T extends Person> T find(T patient) throws PersonNotFoundException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", patient.getUniqueId());
        return find(hashMap, patient).get(0); // Returns arrayList of length 1
    }

    public <T extends Person> ArrayList<T> find(HashMap<String, String> params) throws PersonNotFoundException {
        ArrayList<Patient> patients = daoPatient.find(params);

        if (patients.isEmpty()) {
            throw new PersonNotFoundException("No patients were found with given parameters");
        } else {
            return (ArrayList<T>) patients;
        }
    }

    @Override
    public <T extends Person> ArrayList<T> find(HashMap<String, String> params, T table) throws PersonNotFoundException {
        return find(params);
    }

    @Override
    public <T extends Person> boolean update(T person) {
        return daoPatient.update((Patient) person);
    }

    @Override
    public <T extends Person> boolean delete(T patient, Department department) {
        if (patient.getUniqueId() == null) {
            return false;
        }

        daoPatient.delete(patient.getUniqueId());
        department.remove((Patient) patient);
        return true;
    }

    public boolean checkPatientRegistrationStatus(Patient patient, Department department) {
        return department.getPatients().contains(patient);
    }
}
