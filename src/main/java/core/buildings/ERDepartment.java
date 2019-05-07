package core.buildings;

import core.persons.Bed;
import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;
import java.util.HashMap;

public class ERDepartment extends DepartmentBeds {
    public ERDepartment() {
    }

    public ERDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }

    public ERDepartment(String uniqueId, String name, int totalCapacity) {
        super(uniqueId, name, totalCapacity);
    }

    public ERDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity, HashMap<Patient, Bed> patientsInBeds) {
        super(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds);
    }

    public ERDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity, HashMap<Patient, Bed> patientsInBeds, ArrayList<Patient> patients, ArrayList<Staff> staff) {
        super(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds, patients, staff);
    }
}
