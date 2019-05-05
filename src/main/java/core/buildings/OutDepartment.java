package core.buildings;

import core.persons.Patient;

import java.util.ArrayList;

public class OutDepartment extends Department {
    private ArrayList<Patient> waitingPatients = new ArrayList<>();

    public OutDepartment() {
    }

    public OutDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }
}
