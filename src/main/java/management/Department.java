package management;

import exceptions.*;
import java.util.ArrayList;

public class Department extends Hospital {

	// Setters
	private String name;
	private int capacity;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staff = new ArrayList<Staff>();
	private Bed[] beds;

	public Department(String name,int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.beds = new Bed[capacity];
		for (int id = 0; id < beds.length; id++) {
			beds[id] = new Bed(id);
		}
	}

	public Department() {
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
	}
	public void assign(Patient p) throws ExceededCapacityException {
		if (availableBeds()) {
			if (!getPatients().contains(p)) {
				add(p);
			}
			Bed bed = getAvailableBed();
			bed.fill(p);
		} else {
			throw new ExceededCapacityException("beds " + getAvailableBeds());
		}
	}
	public void assign(Patient p, int id) throws UnavailableBedException, BedNotFoundException {
		if (id > capacity) {
			throw new BedNotFoundException("Invalid ID");
		}
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
	public void move(Patient p, int id) throws UnavailableBedException, BedNotFoundException {
		if (patientInBed(p)) {
			Bed bed = getPatientBed(p);
			assign(p,id);
			bed.empty();
		} else {
			assign(p,id);
		}
	}
	public void removeFromBed(Patient p) {
		if (patientInBed(p)) {
			Bed bed = getPatientBed(p);
			bed.empty();
		}
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

}
