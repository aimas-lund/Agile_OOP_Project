package management;

import exceptions.ExceededCapacityException;
import exceptions.UnavailableBedException;

import java.util.ArrayList;

public class Department {
    private String uniqueId;
	private String name;
	private int capacity;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staff = new ArrayList<Staff>();
    private Bed[] beds = new Bed[0];


    public Department() {
    }

	public Department(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.beds = new Bed[capacity];
		for (int id = 0; id < beds.length; id++) {
			beds[id] = new Bed(id);
		}
	}

	public Department(String uniqueId, String name, int capacity) {
        this(name, capacity);
		this.uniqueId = uniqueId;
	}

	public void add(Staff s) {
		staff.add(s);
	}

	public void add(Patient p) {
		patients.add(p);
	}

	public void remove(Staff s) {
		staff.remove(s);
	}

    public void remove(Patient p) {
        patients.remove(p);

		for (Bed bed : beds) {
			if (bed.getPatient() == p) {
				removeFromBed(p);
			}
		}
	}

    public void removeFromBed(Patient p) {
        if (patientInBed(p)) {
            Bed bed = getPatientBed(p);
            bed.empty();
        }
    }

    public void assign(Patient p) throws ExceededCapacityException {
		if (availableBeds()) {
			if (!getPatients().contains(p)) {
				add(p);
			}
			Bed bed = getAvailableBed();
			bed.fill(p);
		} else {
			throw new ExceededCapacityException("No available beds " + getAvailableBeds());
		}
	}

    public void assign(Patient p, int id) throws UnavailableBedException {
		if (beds[id].isOccupied()) {
			throw new UnavailableBedException("Bed is occupied");
		}
		if (!getPatients().contains(p)) {
			add(p);
		}
		Bed bed = beds[id];
		bed.fill(p);
	}

    public void move(Patient p) throws ExceededCapacityException {
		if (patientInBed(p)) {
			Bed b1 = getPatientBed(p);
			assign(p);
			b1.empty();
		} else {
			assign(p);
		}
	}

    public void move(Patient p, int id) throws UnavailableBedException {
		if (patientInBed(p)) {
			Bed bed = getPatientBed(p);
			assign(p,id);
			bed.empty();
		} else {
			assign(p,id);
		}
	}

    public boolean availableBeds() {
        for (Bed bed : beds) {
            if (bed.getPatient() == null) return true;
        }
        return false;
    }

    public boolean patientInBed(Patient p) {
        for (Bed bed : beds) {
            if (bed.getPatient() == p) {
                return true;
            }
        }
        return false;
    }

	public String getName() {
		return name;
	}

    public int getCapacity() {
		return capacity;
	}

    public Bed[] getBeds() {
		return beds;
	}

    public int getAvailableBeds() {
		int available = 0;
		for (Bed bed : beds) {
			if (bed.getPatient() == null) available++;
		}
		return available;
	}

    public ArrayList<Patient> getPatients() {
		return patients;
	}

    public ArrayList<Staff> getStaff() {
		return staff;
	}

    public Bed getPatientBed(Patient p) {
		for (Bed bed : beds) {
			if (bed.getPatient() == p) {
				return bed;
			}
		}
		return null;
	}

    public Bed getAvailableBed() throws ExceededCapacityException {
		for (Bed bed : beds) {
			if (!bed.isOccupied()) return bed;
		}
		throw new ExceededCapacityException("No available beds");
	}

	public String[] getDepartmentInformation() {
		return new String[]{this.getUniqueId(),this.getName(),String.format("%d",this.getAvailableBeds()),
				String.format("%d",this.getCapacity())};
	}

	void setUniqueId(String uniqueID) {
		this.uniqueId = uniqueID;
	}

	public String getUniqueId() {
		return uniqueId;
	}
}
