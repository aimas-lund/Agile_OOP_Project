package storage;

import exceptions.PersonNotFoundException;
import management.*;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleICT implements IUpdate, IQuery {
    private final Dao<Staff> daoStaff = new DaoStaffImpl<>();
    private final Dao<Patient> daoPatient = new DaoPatientImpl<>();

    @Override
    public <T extends Person> T find(T obj) throws PersonNotFoundException {
        if (obj instanceof Patient) {
            Patient foundPatient = daoPatient.find((Patient) obj);
            if (foundPatient != null) {
                return (T) foundPatient;
            } else {
                throw new PersonNotFoundException("Patient not found in database");
            }
        } else if (obj instanceof Staff) {
            Staff foundStaff = daoStaff.find((Staff) obj);

            if (foundStaff != null) {
                return (T) foundStaff;
            } else {
                throw new PersonNotFoundException("Staff not found in database");
            }
        }
    }

    @Override
    public <T extends Person> ArrayList<T> find(HashMap<String, String> params, T table) throws PersonNotFoundException {
        return null;
    }

    @Override
    public <T extends Person> boolean registerPerson(T person, Department department) {
        return false;
    }

    @Override
    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
        return false;
    }

    @Override
    public <T> boolean delete(T obj, Department department) {
        return false;
    }


    @Override
    public Staff findStaff(Staff staff) throws PersonNotFoundException {
        Staff foundStaff = daoStaff.find(staff);
        if (foundStaff != null) {
            return foundStaff;
        } else {
            throw new PersonNotFoundException("Person not found in database");
        }
    }

    @Override
    public ArrayList<Staff> findStaff(HashMap<String, String> params) throws PersonNotFoundException {
        ArrayList<Staff> staff = daoStaff.find(params);

        if (staff.isEmpty()) {
            throw new PersonNotFoundException("No staff was found with given parameters");
        } else {
            return staff;
        }
    }

    @Override
    public Patient findPatient(Patient patient) throws PersonNotFoundException {
        Patient foundPatient = daoPatient.find(patient);
        if (foundPatient != null) {
            return foundPatient;
        } else {
            throw new PersonNotFoundException("Person not found in database");
        }

    }

    @Override
    public ArrayList<Patient> findPatient(HashMap<String, String> params) throws PersonNotFoundException {
        ArrayList<Patient> patient = daoPatient.find(params);

        if (patient.isEmpty()) {
            throw new PersonNotFoundException("No patient was found with given parameters");
        } else {
            return patient;
        }
    }

    public boolean delete(Staff staff, Department department) {
        if (daoStaff.delete(staff)) {
            department.remove(staff);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param person     with all members not null
     * @param department to add person to
     * @return boolean representing if the registering was successful
     */
    public boolean registerPerson(Staff person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        // Give unique id
        addUniqueIdToPerson(person);

        // Give person email
        String email = InformationGenerator.generateEmail(person);
        person.setEmail(email);

        // Add person to database
        daoStaff.save(person);
        department.getStaff().add(person);
        return true;
    }

    // TODO: Search in database instead

    public boolean isPersonRegistered(Staff person, Department department) {
        // Search for same Unique ID
        for (Person staff : department.getStaff()) {
            // TODO: Optimize find functionality, now is O(n)
            if (staff.getUniqueId() == person.getUniqueId()) {
                return true;
            }
        }
        return false;
    }

    private void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

}
