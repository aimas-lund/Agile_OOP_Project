package core.buildings;

import core.persons.Bed;
import core.persons.Patient;
import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

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

}
