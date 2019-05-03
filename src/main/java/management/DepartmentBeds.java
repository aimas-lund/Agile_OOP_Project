package management;

import exceptions.ExceededCapacityException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class DepartmentBeds extends Department {
    private final int totalCapacity;
    private Bed[] beds = new Bed[0];
    private HashMap<Patient, Bed> patientsInBeds = new HashMap<>();
    private int currentCapacity = 0;


    public DepartmentBeds() {
        totalCapacity = 0;
    }

    public DepartmentBeds(String uniqueId) {
        super(uniqueId);
        totalCapacity = 0;
    }

    public DepartmentBeds(String uniqueId, String name) {
        super(uniqueId, name);
        totalCapacity = 0;

    }

    public DepartmentBeds(String uniqueId, String name, int totalCapacity) {
        super(uniqueId, name);
        this.totalCapacity = totalCapacity;
        this.currentCapacity = totalCapacity;

        beds = new Bed[totalCapacity];

        for (int i = 0; i < totalCapacity; i++) {
            beds[i] = new Bed(i);
        }
    }

    public DepartmentBeds(String uniqueId, String name, int totalCapacity, int currentCapacity,
                          HashMap<Patient, Bed> patientsInBeds) {
        this(uniqueId, name, totalCapacity);
        this.currentCapacity = currentCapacity;

        for (Map.Entry<Patient, Bed> entry : patientsInBeds.entrySet()) {
            beds[entry.getValue().getId()] = entry.getValue();
            add(entry.getKey());
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
        if (available.isEmpty()) {
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
        return new String[]{this.getUniqueId(), this.getClass().getCanonicalName(), String.valueOf(this.currentCapacity),
                String.valueOf(this.totalCapacity)};
    }


    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
}
