package core.buildings;

import core.persons.Patient;
import core.persons.Staff;
import exceptions.ExceededCapacityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static core.buildings.Event.UPDATE;

public abstract class DepartmentBeds extends Department {
    private final int totalCapacity;
    private Bed[] beds = new Bed[0];
    private HashMap<Patient, Bed> patientsInBeds = new HashMap<>();
    private int currentCapacity = 0;

    DepartmentBeds() {
        super();
        totalCapacity = 0;
    }

    DepartmentBeds(String uniqueId, String name) {
        super(uniqueId, name);
        totalCapacity = 0;
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity) {
        this(uniqueId, name, totalCapacity, new ArrayList<>(), new ArrayList<>());
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity, ArrayList<Patient> patients, ArrayList<Staff> staff) {
        super(uniqueId, name, patients, staff);
        this.totalCapacity = totalCapacity;
        this.currentCapacity = totalCapacity;
        beds = new Bed[totalCapacity];

        for (int i = 0; i < totalCapacity; i++) {
            beds[i] = new Bed(i);
        }
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity, int currentCapacity,
                          HashMap<Patient, Bed> patientsInBeds) {
        this(uniqueId, name, totalCapacity, currentCapacity, patientsInBeds, new ArrayList<>(), new ArrayList<>());
    }

    DepartmentBeds(String uniqueId, String name, int totalCapacity, int currentCapacity,
                   HashMap<Patient, Bed> patientsInBeds, ArrayList<Patient> patients, ArrayList<Staff> staff) {
        this(uniqueId, name, totalCapacity, patients, staff);
        this.currentCapacity = currentCapacity;
        this.patientsInBeds = patientsInBeds;

        for (Map.Entry<Patient, Bed> entry : patientsInBeds.entrySet()) {
            Patient patient = entry.getKey();
            Bed bed = entry.getValue();
            bed.fill(patient);
            add(patient);
            beds[bed.getId()] = bed;

        }
    }

    /**
     * @param bedId id of the bed to check for, a number greater than 0 and less than total capacity
     * @return true if bed is occupied, false otherwise
     */
    Boolean isOccupied(int bedId) {
        if (bedId >= totalCapacity) {
            return true;
        }

        return beds[bedId].isOccupied();
    }

    /**
     * Adds patient to hashmap of patients as keys and beds as values
     *
     * @param patient with at least unique id
     * @param bed object with an id
     */
    void addPatientInBed(Patient patient, Bed bed) {
        patientsInBeds.put(patient, bed);
        this.notifyListeners(this, UPDATE, null, patient);
    }

    /**
     * Removes patient to hashmap of patients as keys and beds as values
     *
     * @param patient with at least unique id
     */
    void removePatientInBed(Patient patient) {
        patientsInBeds.remove(patient);
        this.notifyListeners(this, UPDATE, null, patient);
    }

    void incrementCurrentCapacity() {
        currentCapacity++;
        notifyListeners(this, UPDATE, this, this);
    }

    void decrementCurrentCapacity() {
        currentCapacity--;
        notifyListeners(this, UPDATE, this, this);
    }

    /**
     * @return true if current capacity is greater than 0 false otherwise
     */
    public boolean hasAvailableBeds() {
        return currentCapacity > 0;
    }

    /**
     * @param p with at least unique id
     * @return bed object which p is associated with
     */
    public Bed getBedWithPatient(Patient p) {
        return patientsInBeds.get(p);
    }

    public HashMap<Patient, Bed> getPatientsInBeds() {
        return patientsInBeds;
    }

    /**
     * @return bed objects in ArrayList that are not occupied
     * @throws ExceededCapacityException if there is no available beds
     */
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
