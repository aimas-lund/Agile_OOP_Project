package management;

import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

import java.util.ArrayList;

public class InDepartment extends Department {
    private Bed[] beds = new Bed[0];
    //    private patientsInBeds
    private int capacity;

    public InDepartment() {
    }

    public InDepartment(String uniqueId) {
        super(uniqueId);
    }

    public InDepartment(String uniqueId, String name) {
        super(uniqueId, name);
    }

    public InDepartment(String uniqueId, String name, int capacity) {
        super(uniqueId, name);
        this.capacity = capacity;

        beds = new Bed[capacity];

        for (int i = 0; i < capacity; i++) {
            beds[i] = new Bed(i);
        }
    }

    public void assign(Patient p) throws ExceededCapacityException {
        if (hasAvailableBeds()) {
            if (!getPatients().contains(p)) {
                add(p);
            }
            Bed bed = getAvailableBeds().get(0);
            bed.fill(p);
        } else {
            throw new ExceededCapacityException("No available beds ");
        }
    }

    public void assign(Patient p, int bedId) throws UnavailableBedException {
        if (beds[bedId].isOccupied()) {
            throw new UnavailableBedException("Bed is occupied");
        }
        if (!getPatients().contains(p)) {
            add(p);
        }
        Bed bed = beds[bedId];
        bed.fill(p);
    }

    public void discharge(Patient p) {
        remove(p);
        removeFromBed(p);
    }

    public void removeFromBed(Patient p) {
        if (patientInBed(p)) {
            Bed bed = getBedWithPatient(p);
            bed.empty();
        }
    }

    public void move(Patient p) throws ExceededCapacityException {
        if (patientInBed(p)) {
            Bed b1 = getBedWithPatient(p);
            assign(p);
            b1.empty();
        } else {
            assign(p);
        }
    }

    public void move(Patient p, int id) throws UnavailableBedException {
        if (patientInBed(p)) {
            Bed bed = getBedWithPatient(p);
            assign(p, id);
            bed.empty();
        } else {
            assign(p, id);
        }
    }

    public boolean hasAvailableBeds() {
        // TODO: Can add observer
        for (Bed bed : beds) {
            if (bed.getPatient() == null) return true;
        }
        return false;
    }

    public Bed getBedWithPatient(Patient p) {
        for (Bed bed : beds) {
            if (bed.getPatient() == p) {
                return bed;
            }
        }
        return null;
    }


    public ArrayList<Bed> getAvailableBeds() throws ExceededCapacityException {
        ArrayList<Bed> available = new ArrayList<>();
        for (Bed bed : beds) {
            if (bed.getPatient() == null) {
                available.add(bed);
            }
        }
        if (available.size() == 0) {
            throw new ExceededCapacityException("No available beds");
        }
        return available;
    }

    public boolean patientInBed(Patient p) {
        for (Bed bed : beds) {
            if (bed.getPatient() == p) {
                return true;
            }
        }
        return false;
    }

    public Bed[] getBeds() {
        return beds;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String[] getDepartmentInformation() {
        return new String[]{this.getUniqueId(), this.getClass().getCanonicalName(), this.g};
    }


}
