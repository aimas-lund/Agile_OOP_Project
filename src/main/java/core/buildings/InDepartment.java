package core.buildings;

import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;
import java.util.HashMap;

public class InDepartment extends DepartmentBeds {

    public InDepartment() {
    }

    public InDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }

    public InDepartment(String uniqueId, String name, int totalCapacity) {
        super(uniqueId, name, totalCapacity);
    }

    public InDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity, HashMap<Patient, Bed> patientsInBeds) {
        super(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds);
    }

    public InDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity, HashMap<Patient, Bed> patientsInBeds, ArrayList<Patient> patients, ArrayList<Staff> staff) {
        super(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds, patients, staff);
    }

}
