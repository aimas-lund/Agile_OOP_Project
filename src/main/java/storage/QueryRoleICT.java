package storage;

import exceptions.PersonNotFoundException;
import management.*;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleICT implements IUpdate, IQuery {
    private final DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();
    private final DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

    public <T extends Patient> T find(Patient patient) throws PersonNotFoundException {
        Patient foundPatient = daoPatient.find(patient);
        if (foundPatient != null) {
            return (T) foundPatient;
        } else {
            throw new PersonNotFoundException("Patient not found in database");
        }
    }

    public <T extends Staff> T find(Staff staff) throws PersonNotFoundException {
        Staff foundStaff = daoStaff.find(staff);
        if (foundStaff != null) {
            return (T) foundStaff;
        } else {
            throw new PersonNotFoundException("Staff not found in database");
        }
    }

    public ArrayList<Staff> find(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Staff());
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
    public <T extends Person> boolean delete(T obj, Department department) {
        return false;
    }

    @Override
    public <T extends Person> boolean update(T person) {
        return false;
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
        InformationGenerator.generateUniqueID(person);

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

}
