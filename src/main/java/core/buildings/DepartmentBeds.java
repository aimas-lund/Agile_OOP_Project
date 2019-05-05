package core.buildings;

import core.persons.Bed;
import core.persons.Patient;
import exceptions.ExceededCapacityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DepartmentBeds extends Department {
    private final int totalCapacity;
    private Bed[] beds = new Bed[0];
    private HashMap<Patient, Bed> patientsInBeds = new HashMap<>();
    private int currentCapacity = 0;

    DepartmentBeds() {
        totalCapacity = 0;
    }

    DepartmentBeds(String uniqueId, String name) {
        super(uniqueId, name);
        totalCapacity = 0;
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity) {
        super(uniqueId, name);
        this.totalCapacity = totalCapacity;
        this.currentCapacity = totalCapacity;

        beds = new Bed[totalCapacity];

        for (int i = 0; i < totalCapacity; i++) {
            beds[i] = new Bed(i);
        }
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity, int currentCapacity,
                          HashMap<Patient, Bed> patientsInBeds) {
        this(uniqueId, name, totalCapacity);
        this.currentCapacity = currentCapacity;
        this.patientsInBeds = patientsInBeds;

        for (Map.Entry<Patient, Bed> entry : patientsInBeds.entrySet()) {
            Bed bed = entry.getValue();
            Patient patient = entry.getKey();
            bed.fill(patient);
            add(patient);
            beds[bed.getId()] = bed;
        }
    }

    Boolean isOccupied(int bedId) {
        if (bedId >= totalCapacity) {
            return true;
        }

        return beds[bedId].isOccupied();
    }

    void addPatientInBed(Patient patient, Bed bed) {
        patientsInBeds.put(patient, bed);
    }

    void removePatientInBed(Patient patient) {
        patientsInBeds.remove(patient);
    }

    void incrementCurrentCapacity() {
        currentCapacity++;
    }

    void decrementCurrentCapacity() {
        currentCapacity--;
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
            if (!bed.isOccupied()) {
                available.add(bed);
            }
        }
        if (available.isEmpty()) {
            throw new ExceededCapacityException("No available beds");
        }
        return available;
    }

    public boolean isPatientInBed(Patient p) {
        return patientsInBeds.containsKey(p);
    }

    public Bed[] getBeds() {
        return beds;
    }

    public Bed getBed(int bedId) {
        if (bedId >= totalCapacity) {
            return null;
        }

        return beds[bedId];
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    @Override
    public String[] getDepartmentInformation() {
        return new String[]{this.getUniqueId(), this.getName(), this.getClass().getSimpleName(), String.valueOf(this.currentCapacity),
                String.valueOf(this.totalCapacity)};
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

}
