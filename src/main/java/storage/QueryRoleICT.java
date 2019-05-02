package storage;

import exceptions.PersonNotFoundException;
import management.*;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleICT implements IUpdate, IQuery {
    private final DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();
    private final DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

    public <T extends Patient> T find(Patient patient) throws PersonNotFoundException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", patient.getUniqueId());
        return (T) findPatient(hashMap).get(0);
    }

    public <T extends Staff> T find(Staff staff) throws PersonNotFoundException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("uniqueid", staff.getUniqueId());
        return (T) findStaff(hashMap).get(0);
    }

    public ArrayList<Staff> find(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Staff());
    }

    @Override
    public <T extends Person> ArrayList<T> find(HashMap<String, String> params, T type) throws PersonNotFoundException {
        ArrayList<T> persons = new ArrayList<>(0);

        if (type instanceof Patient) {
            persons = (ArrayList<T>) daoPatient.find(params);

        } else if (type instanceof Staff) {
            persons = (ArrayList<T>) daoStaff.find(params);
        }

        if (persons.isEmpty()) {
            throw new PersonNotFoundException("No person were found with given parameters");
        } else {
            return persons;
        }
    }

// TODO: Revisit
//    private <T extends Person> String getDatabaseTable(T type) {
//        for (Annotation annotation: type.getClass().getAnnotations()) {
//            if (annotation instanceof Table) {
//                return ((Table) annotation).name();
//            }
//        }
//        return null;
//    }

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
     * @param staff     with all members not null
     * @param department to add person to
     * @return boolean representing if the registering was successful
     */
    public boolean registerPerson(Staff staff, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(staff, department)) {
            return false;
        }

        // Give unique id
        if (staff.getUniqueId() == null) {
            String uniqueID = InformationGenerator.generateUniqueID(staff);
            new PersonInformationFacade(staff).setPersonUniqueId(uniqueID);
        }

        if (staff.getEmail() == null) {
            // Give person email
            String email = InformationGenerator.generateEmail(staff);
            new PersonInformationFacade(staff).setStaffEmail(email);
        }

        // Add person to database
        daoStaff.save(staff);
        department.getStaff().add(staff);
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
