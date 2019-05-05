package core.buildings;

import core.persons.Patient;

import java.util.LinkedList;
import java.util.Queue;

public class OutDepartment extends Department {
    private Queue<Patient> waitingPatients = new LinkedList<>();

    public OutDepartment() {
    }

    public OutDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }

    public void addWaitingPatient(Patient patient) {
        if (!isPatientInDepartment(patient)) {
            add(patient);
        }
        waitingPatients.add(patient);
    }

    public Patient getNextWaitingPatient() {
        return waitingPatients.poll();
    }

    public boolean hasWaitingPatients() {
        return waitingPatients.peek() != null;
    }
}
