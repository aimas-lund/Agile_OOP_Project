package core.buildings;

import core.persons.Bed;
import core.persons.Patient;
import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

public class BedManager {
    private final DepartmentBeds department;

    public BedManager(DepartmentBeds department) {
        this.department = department;
    }

    public void assignToBed(Patient p, int bedId) throws UnavailableBedException {
        if (department.isOccupied(bedId)) {
            throw new UnavailableBedException("Bed is occupied");
        }

        if (!department.isPatientInDepartment(p)) {
            department.add(p);
        }

        Bed bed = department.getBed(bedId);

        bed.fill(p);
        department.addPatientInBed(p, bed);
        department.decrementCurrentCapacity();

    }

    public void assignToBed(Patient p) throws ExceededCapacityException {
        if (department.hasAvailableBeds()) {
            if (!department.isPatientInDepartment(p)) {
                department.add(p);
            }
            Bed bed = department.getAvailableBeds().get(0);
            bed.fill(p);
            department.addPatientInBed(p, bed);
            department.decrementCurrentCapacity();

        } else {
            throw new ExceededCapacityException("No available beds");
        }
    }

    public void discharge(Patient p) {
        department.remove(p);
        removeFromBed(p);
        department.incrementCurrentCapacity();
    }

    public void removeFromBed(Patient p) {
        if (department.patientInBed(p)) {
            Bed bed = department.getBedWithPatient(p);
            bed.empty();
            department.removePatientInBed(p);
            department.incrementCurrentCapacity();
        }
    }

    public void changeBed(Patient p) throws ExceededCapacityException {
        if (department.patientInBed(p) && department.hasAvailableBeds()) {
            Bed bed = department.getAvailableBeds().get(0);
            removeFromBed(p);
            try {
                assignToBed(p, bed.getId());
            } catch (UnavailableBedException e) {
                e.printStackTrace();
            }
        } else {
            assignToBed(p);
        }
    }

    public void changeBed(Patient p, int id) throws UnavailableBedException {
        if (department.patientInBed(p)) {
            removeFromBed(p);
            assignToBed(p, id);
        } else {
            assignToBed(p, id);
        }
    }

}
