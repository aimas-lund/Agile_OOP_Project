package core.buildings;

import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OutDepartment extends Department {
    private Queue<Patient> waitingPatients = new LinkedList<>();

    public OutDepartment() {
    }

    public OutDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }

    public OutDepartment(String uniqueId, String name, Queue<Patient> waitingPatients) {
        this(uniqueId, name, new ArrayList<>(), new ArrayList<>());
        this.waitingPatients = waitingPatients;
    }

    public OutDepartment(String uniqueId, String name, ArrayList<Patient> patients, ArrayList<Staff> staff) {
        super(uniqueId, name, patients, staff);
    }

    public OutDepartment(String uniqueId, String name, ArrayList<Patient> patients, ArrayList<Staff> staff, Queue<Patient> waitingPatients) {
        this(uniqueId, name, patients, staff);
        this.waitingPatients = waitingPatients;
    }


    public void addWaitingPatient(Patient patient) {
        if (!isPatientInDepartment(patient)) {
            add(patient);
        }
        waitingPatients.add(patient);
    }

    public boolean isPatientWaiting(Patient patient) {
        return waitingPatients.contains(patient);
    }

    public Patient getNextWaitingPatient() {
        return waitingPatients.poll();
    }

    public boolean hasWaitingPatients() {
        return waitingPatients.peek() != null;
    }

    public Queue<Patient> getWaitingPatients() {
        return waitingPatients;
    }
}
