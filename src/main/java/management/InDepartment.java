package management;

import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InDepartment extends Department {
    private Bed[] beds = new Bed[0];
    private final int totalCapacity;
    private HashMap<Patient, Bed> patientsInBeds = new HashMap<>();
    private int currentCapacity = 0;

    public InDepartment() {
        totalCapacity = 0;
    }

    public InDepartment(String uniqueId) {
        super(uniqueId);
        totalCapacity = 0;

    }

    public InDepartment(String uniqueId, String name) {
        super(uniqueId, name);
        totalCapacity = 0;

    }

    public InDepartment(String uniqueId, String name, int totalCapacity) {
        super(uniqueId, name);
        this.totalCapacity = totalCapacity;
        this.currentCapacity = totalCapacity;

        beds = new Bed[totalCapacity];

        for (int i = 0; i < totalCapacity; i++) {
            beds[i] = new Bed(i);
        }
    }

    public InDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity) {
        this(uniqueId, name, totalCapacity);
        this.currentCapacity = currentCapacity;
    }

    public InDepartment(String uniqueId, String name, int totalCapacity, int currentCapacity,
                        HashMap<Patient, Bed> patientsInBeds) {
        this(uniqueId, name, totalCapacity, currentCapacity);

        for (Map.Entry<Patient, Bed> entry : patientsInBeds.entrySet()) {
            beds[entry.getValue().getId()] = entry.getValue();
            add(entry.getKey());
        }
    }

    public void assign(Patient p) throws ExceededCapacityException {
        if (hasAvailableBeds()) {
            if (!getPatients().contains(p)) {
                add(p);
            }
            Bed bed = getAvailableBeds().get(0);
            bed.fill(p);
            patientsInBeds.put(p, bed);
            currentCapacity--;

        } else {
            throw new ExceededCapacityException("No available beds");
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
        patientsInBeds.put(p, bed);
        currentCapacity--;
    }

    public void discharge(Patient p) {
        remove(p);
        removeFromBed(p);
        currentCapacity++;
    }

    public void removeFromBed(Patient p) {
        if (patientInBed(p)) {
            Bed bed = getBedWithPatient(p);
            bed.empty();
            patientsInBeds.remove(p);
            currentCapacity++;
        }
    }

    public void move(Patient p) throws ExceededCapacityException {
        if (patientInBed(p)) {
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

    public boolean hasAvailableBeds() {
        return currentCapacity > 0;
    }

    public Bed getBedWithPatient(Patient p) {
        return patientsInBeds.get(p);
    }

    public HashMap<Patient, Bed> getPatientsInBeds() {
        return patientsInBeds;
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
        return patientsInBeds.containsKey(p);
    }

    public Bed[] getBeds() {
        return beds;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    @Override
    public String[] getDepartmentInformation() {
        return new String[]{this.getUniqueId(), this.getClass().getCanonicalName(), String.valueOf(this.getCurrentCapacity()),
                String.valueOf(this.getTotalCapacity())};
    }


    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
