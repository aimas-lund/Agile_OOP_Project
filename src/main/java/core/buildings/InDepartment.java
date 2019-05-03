package core.buildings;

import core.persons.Bed;
import core.persons.Patient;
import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

import java.util.HashMap;

public class InDepartment extends DepartmentBeds {

    public InDepartment() {
    }

    public InDepartment(String uniqueId) {
        super(uniqueId);
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

    public void assign(Patient p) throws ExceededCapacityException {
        if (hasAvailableBeds()) {
            if (!getPatients().contains(p)) {
                add(p);
            }
            Bed bed = getAvailableBeds().get(0);
            bed.fill(p);
            getPatientsInBeds().put(p, bed);
            setCurrentCapacity(getCurrentCapacity() - 1);

        } else {
            throw new ExceededCapacityException("No available beds");
        }
    }

    public void assign(Patient p, int bedId) throws UnavailableBedException {
        if (getBeds()[bedId].isOccupied()) {
            throw new UnavailableBedException("Bed is occupied");
        }
        if (!getPatients().contains(p)) {
            add(p);
        }
        Bed bed = getBeds()[bedId];
        bed.fill(p);
        getPatientsInBeds().put(p, bed);
        setCurrentCapacity(getCurrentCapacity() - 1);

    }

    public void discharge(Patient p) {
        remove(p);
        removeFromBed(p);
        setCurrentCapacity(getCurrentCapacity() + 1);
    }

    public void removeFromBed(Patient p) {
        if (patientInBed(p)) {
            Bed bed = getBedWithPatient(p);
            bed.empty();
            getPatientsInBeds().remove(p);
            setCurrentCapacity(getCurrentCapacity() + 1);
        }
    }

    public void move(Patient p) throws ExceededCapacityException {
        if (patientInBed(p) && hasAvailableBeds()) {
            removeFromBed(p);
            assign(p);
        } else {
            assign(p);
        }
    }

    public void move(Patient p, int id) throws UnavailableBedException {
        if (patientInBed(p)) {
            removeFromBed(p);
            assign(p, id);
        } else {
            assign(p, id);
        }
    }

}
