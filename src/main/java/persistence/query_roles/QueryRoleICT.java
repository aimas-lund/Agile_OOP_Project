package persistence.query_roles;

import core.buildings.Department;
import core.persons.Patient;
import core.persons.Person;
import core.persons.PersonInformationFacade;
import core.persons.Staff;
import core.utility.InformationGenerator;
import exceptions.PersonNotFoundException;
import persistence.data_access_objects.DaoPatientImpl;
import persistence.data_access_objects.DaoStaffImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class QueryRoleICT implements IUpdate, IQuery {
    private final DaoStaffImpl<Staff> daoStaff = new DaoStaffImpl<>();
    private final DaoPatientImpl<Patient> daoPatient = new DaoPatientImpl<>();

    @Override
    public <T extends Person> boolean registerPerson(T person, Department department) {
        if (isPersonRegistered(person, department)) {
            return false;
        }

        if (person.getUniqueId() == null) {
            String uniqueId = InformationGenerator.generateUniqueID();
            new PersonInformationFacade(person).setPersonUniqueId(uniqueId);
        }

        boolean success = false;

        if (person instanceof Staff) {
            if (((Staff) person).getEmail() == null) {
                String email = InformationGenerator.generateEmail((Staff) person);
                new PersonInformationFacade(person).setStaffEmail(email);
            }

            success = daoStaff.save((Staff) person);
            department.add((Staff) person);

        } else if (person instanceof Patient) {
            success = daoPatient.save((Patient) person);
            department.add((Patient) person);
        }

        return success;
    }

    @Override
    public <T extends Person> boolean isPersonRegistered(T person, Department department) {
        if (person instanceof Staff) {
            for (Staff staff : department.getStaff()) {
                if (staff.getUniqueId().equals(person.getUniqueId())) return true;
            }
        } else if (person instanceof Patient) {
            for (Patient patient : department.getPatients()) {
                if (patient.getUniqueId().equals(person.getUniqueId())) return true;
            }
        }
        return false;
    }

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

    public ArrayList<Staff> findStaff(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Staff());
    }

    public ArrayList<Patient> findPatient(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Patient());
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

    @Override
    public <T extends Person> boolean delete(T person, Department department) {
        if (person instanceof Staff) {
            if (daoStaff.delete((Staff) person)) {
                department.remove((Staff) person);
                return true;
            }
        } else if (person instanceof Patient) {
            if (daoPatient.delete((Patient) person)) {
                department.remove((Patient) person);
                return true;
            }
        }
        return false;
    }

    @Override
    public <T extends Person> boolean update(T person) {
        if (person instanceof Patient) {
            return daoPatient.update((Patient) person);
        } else if (person instanceof Staff) {
            return daoStaff.update((Staff) person);
        }
        return false;
    }
}
