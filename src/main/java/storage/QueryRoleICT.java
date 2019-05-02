package storage;

import exceptions.PersonNotFoundException;
import management.*;

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
            String uniqueId = InformationGenerator.generateUniqueID(person);
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

    public ArrayList<Staff> find(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Staff());
    }

    public ArrayList<Staff> findStaff(HashMap<String, String> params) throws PersonNotFoundException {
        return find(params, new Staff());
    }

        if (persons.isEmpty()) {
            throw new PersonNotFoundException("No person were found with given parameters");
        } else {
            return persons;
        }
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


    public boolean delete(Staff staff, Department department) {
        if (daoStaff.delete(staff)) {
            department.remove(staff);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(Patient patient, Department department) {
        if (daoPatient.delete(patient)) {
            department.remove(patient);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public <T extends Person> boolean delete(T person, Department department) {
        if (person instanceof Staff) {
            return delete((Staff) person, department);
        } else if (person instanceof Patient) {
            return delete((Patient) person, department);
        }
        return false;
    }

    @Override
    public <T extends Person> boolean update(T person) {
        return false;
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
}
